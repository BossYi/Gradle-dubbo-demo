package priv.yimeng.demo.persistence.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import priv.yimeng.demo.persistence.Filter;
import priv.yimeng.demo.persistence.Order;
import priv.yimeng.demo.persistence.Page;
import priv.yimeng.demo.persistence.Pageable;
import priv.yimeng.demo.persistence.domain.BaseDO;
import priv.yimeng.demo.persistence.domain.BaseOrderDO;
import priv.yimeng.demo.persistence.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.*;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-25
 *
 * @author yimeng
 * @version 1.0
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    /**
     * SQL通配转义符
     */
    private static final char SQL_LIKE_ESCAPE = '/';

    /**
     * 属性分割符
     */
    private static final String PROPERTY_SEPARATOR = ".";

    private final Class<T> domainClass;
    private final EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
        this.entityManager = entityManager;
    }

    @Override
    public T getOneObject(ID id) {
        return entityManager.find(domainClass, id);
    }

    @Override
    public List<T> list(Filter... filters) {
        return list(null, null, filters != null ? Arrays.asList(filters) : null, null);
    }

    @Override
    public List<T> list(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(domainClass);
        criteriaQuery.select(criteriaQuery.from(domainClass));
        return list(criteriaQuery, first, count, filters, orders);
    }

    @Override
    public List<T> list(String querySelectName, Integer first, Integer count, List<Filter> filters, List<Order> orders) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(domainClass);
        if (StringUtils.isNotEmpty(querySelectName)) {
            buildQuerySelect(querySelectName, query);
        }
        return null;
    }

    private List<T> list(CriteriaQuery<T> criteriaQuery, Integer first, Integer count, List<Filter> filters, List<Order> orders) {
        Assert.notNull(criteriaQuery, "criteriaQuery must not be null!");
        Assert.notNull(criteriaQuery.getSelection(), "selection must not be null!");
        Assert.notNull(criteriaQuery.getRoots(), "roots must not be null!");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<T> root = getRoot(criteriaQuery);
        addRestrictions(criteriaQuery, filters);
        addOrders(criteriaQuery, orders);

        // 如果没有指定排序，使用默认排序
        if (criteriaQuery.getOrderList().isEmpty()) {
            if (BaseOrderDO.class.isAssignableFrom(domainClass)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(BaseOrderDO.ORDER_PROPERTY_NAME)));
            } else if (BaseDO.class.isAssignableFrom(domainClass)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(BaseDO.ID_PROPERTY_NAME)));
            }
        }
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
        if (first != null) {
            query.setFirstResult(first);
        }
        if (count != null) {
            query.setMaxResults(count);
        }
        return query.getResultList();
    }

    private Root<T> getRoot(CriteriaQuery<T> criteriaQuery) {
        Assert.notNull(criteriaQuery, "criteriaQuery must not be null!");
        return getRoot(criteriaQuery, criteriaQuery.getResultType());
    }

    private Root<T> getRoot(CriteriaQuery<T> criteriaQuery, Class<T> resultType) {
        if (resultType == null || CollectionUtils.isEmpty(criteriaQuery.getRoots())) {
            return null;
        }
        Set<Root<?>> roots = criteriaQuery.getRoots();
        for (Root<?> root : roots) {
            if (resultType.equals(root.getJavaType())) {
                return (Root<T>) root.as(resultType);
            }
        }
        return null;
    }

    private void buildQuerySelect(String querySelectName, CriteriaQuery<T> query) {
    }


    private void addRestrictions(CriteriaQuery<T> criteriaQuery, List<Filter> filters) {
        if (criteriaQuery == null || filters == null || filters.isEmpty()) {
            return;
        }
        Root<T> root = getRoot(criteriaQuery);
        if (root == null) {
            return;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Predicate predicate = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction() : criteriaBuilder.conjunction();
        predicate = addFilters(filters, root, criteriaBuilder, predicate);
        criteriaQuery.where(predicate);
    }

    /**
     * filters {@link Filter}转化为{@link CriteriaQuery}的{@link Predicate}
     *
     * @param filters         filterList 筛选条件
     * @param root            root
     * @param criteriaBuilder criteriaBuilder
     * @param predicate       构造条件
     * @return predicate
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private Predicate addFilters(List<Filter> filters, Root<T> root, CriteriaBuilder criteriaBuilder, Predicate predicate) {
        for (Filter filter : filters) {
            if (filter == null || StringUtils.isEmpty(filter.getProperty())) {
                throw new NullPointerException("the filter or property for filter must not null!");
            }
            String[] properties = StringUtils.split(filter.getProperty(), ",");
            Path[] paths = new Path[properties.length];
            for (int i = 0; i < properties.length; i++) {
                String property = properties[i];
                String[] tempPaths = StringUtils.split(property, PROPERTY_SEPARATOR);
                From<?, ?> from = root;
                // 存在 property = "A.b"的情况，使用连接查询
                for (int j = 0; j < tempPaths.length - 1; j++) {
                    from = getJoin(from, tempPaths[j]);
                }
                paths[i] = from.get(tempPaths[tempPaths.length - 1]);
            }

            if (filter.getOperator() == Filter.Operator.isNull) {
                predicate = criteriaBuilder.and(predicate, paths[0].isNull());
            } else if (filter.getOperator() == Filter.Operator.isNotNull) {
                predicate = criteriaBuilder.and(predicate, paths[0].isNotNull());
            } else if (filter.getValue() == null && filter.getOperator() != null) {
                throw new NullPointerException("the value of filter for " + filter.getProperty() + " must not null!");
            } else if (filter.getOperator() == Filter.Operator.eq) {
                if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
                    predicate = criteriaBuilder.and(predicate,
                            criteriaBuilder.equal(criteriaBuilder.lower(paths[0]), ((String) filter.getValue()).toLowerCase()));
                } else {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(paths[0], filter.getValue()));
                }
            } else if (filter.getOperator() == Filter.Operator.ne) {
                if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
                    predicate = criteriaBuilder.and(predicate,
                            criteriaBuilder.notEqual(criteriaBuilder.lower(paths[0]), ((String) filter.getValue()).toLowerCase()));
                } else {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.notEqual(paths[0], filter.getValue()));
                }
            } else if (filter.getOperator() == Filter.Operator.gt) {
                if (filter.getValue() instanceof Number) {
                    predicate = criteriaBuilder.and(predicate,
                            criteriaBuilder.gt(paths[0], (Number) filter.getValue()));
                } else {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThan(paths[0], (Comparable) filter.getValue()));
                }
            } else if (filter.getOperator() == Filter.Operator.lt) {
                if (filter.getValue() instanceof Number) {
                    predicate = criteriaBuilder.and(predicate,
                            criteriaBuilder.lt(paths[0], (Number) filter.getValue()));
                } else {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThan(paths[0], (Comparable) filter.getValue()));
                }
            } else if (filter.getOperator() == Filter.Operator.ge) {
                if (filter.getValue() instanceof Number) {
                    predicate = criteriaBuilder.and(predicate,
                            criteriaBuilder.ge(paths[0], (Number) filter.getValue()));
                } else {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(paths[0], (Comparable) filter.getValue()));
                }
            } else if (filter.getOperator() == Filter.Operator.le) {
                if (filter.getValue() instanceof Number) {
                    predicate = criteriaBuilder.and(predicate,
                            criteriaBuilder.le(paths[0], (Number) filter.getValue()));
                } else {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(paths[0], (Comparable) filter.getValue()));
                }
            } else if (filter.getOperator() == Filter.Operator.like && filter.getValue() instanceof String) {
                String value = filter.getValue().toString();
                if (filter.getAutoWildcard()) {
                    value = escapeSqlLike(value, SQL_LIKE_ESCAPE);
                    value = "%" + value + "%";
                }
                Predicate likePredicate = criteriaBuilder.like(paths[0], value, SQL_LIKE_ESCAPE);
                for (int i = 1; i < paths.length; i++) {
                    Predicate temp = criteriaBuilder.like(paths[i], value, SQL_LIKE_ESCAPE);
                    likePredicate = criteriaBuilder.or(likePredicate, temp);
                }
                predicate = criteriaBuilder.and(predicate, likePredicate);
            } else if (filter.getOperator() == Filter.Operator.in) {
                Object value = filter.getValue();
                Predicate in;
                if (value instanceof Object[]) {
                    if (((Object[]) value).length == 0) {
                        throw new IndexOutOfBoundsException("the value of filter for " + filter.getProperty() + " must not empty array!");
                    }
                    in = paths[0].in((Object[]) value);
                } else if (value instanceof Collection) {
                    if (((Collection) value).size() == 0) {
                        throw new IndexOutOfBoundsException("the value of filter for " + filter.getProperty() + " must not empty collection!");
                    }
                    in = paths[0].in((Collection) value);
                } else {
                    in = paths[0].in(value);
                }
                predicate = criteriaBuilder.and(predicate, in);
            }
        }
        return predicate;
    }

    private String escapeSqlLike(String likeSql, char escape) {
        likeSql = likeSql.replace(escape + "", escape + "" + escape);
        likeSql = likeSql.replace("%", escape + "%");
        likeSql = likeSql.replace("_", escape + "_");
        likeSql = likeSql.replace("[", escape + "[");
        likeSql = likeSql.replace("]", escape + "]");
        return likeSql;
    }

    private <X, Y> Join<Y, ?> getJoin(From<X, Y> from, String tempPath) {
        Set<Join<Y, ?>> joins = from.getJoins();
        for (Join<Y, ?> join : joins) {
            if (tempPath.equals(join.getAttribute().getName())) {
                return join;
            }
        }
        return from.join(tempPath);
    }

    private <X, Y> Join<Y, ?> getJoin(From<X, Y> from, String tempPath, JoinType joinType) {
        Set<Join<Y, ?>> joins = from.getJoins();
        for (Join<Y, ?> join : joins) {
            if (tempPath.equals(join.getAttribute().getName())) {
                return join;
            }
        }
        return from.join(tempPath, joinType);
    }

    /**
     * 添加排序
     *
     * @param criteriaQuery criteriaQuery
     * @param orders        排序对象集合
     */
    private void addOrders(CriteriaQuery<T> criteriaQuery, List<Order> orders) {
        if (criteriaQuery == null || orders == null || orders.isEmpty()) {
            return;
        }
        Root<T> root = getRoot(criteriaQuery);
        if (root == null) {
            return;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        List<javax.persistence.criteria.Order> orderList = new ArrayList<>();
        if (!criteriaQuery.getOrderList().isEmpty()) {
            orderList.addAll(criteriaQuery.getOrderList());
        }
        for (Order order : orders) {
            Path<T> path = root;
            for (String property : StringUtils.split(order.getProperty(), PROPERTY_SEPARATOR)) {
                path = path.get(property);
            }
            if (order.getDirection().equals(Order.Direction.asc)) {
                orderList.add(criteriaBuilder.asc(path));
            }
            if (order.getDirection().equals(Order.Direction.desc)) {
                orderList.add(criteriaBuilder.desc(path));
            }
        }
        criteriaQuery.orderBy(orderList);
    }

    @Override
    public Page<T> listPage(Pageable pageable) {
        return null;
    }

    @Override
    public Page<T> listPage(CriteriaQuery<T> criteriaQuery, Pageable pageable) {
        return null;
    }

    @Override
    public Long count(Filter... filters) {
        return null;
    }

    @Override
    public void persist(T entity) {
        Assert.notNull(entity, "null");
        entityManager.persist(entity);
    }

    @Override
    public T merge(T entity) {
        return null;
    }

    @Override
    public void remove(T entity) {

    }

    @Override
    public void refresh(T entity) {

    }

    @Override
    public void refresh(T entity, LockModeType lockModeType) {

    }

    @Override
    public ID getIdentifier(T entity) {
        return null;
    }

    @Override
    public boolean isLoaded(T entity) {
        return false;
    }

    @Override
    public boolean isLoaded(T entity, String attributeName) {
        return false;
    }

    @Override
    public boolean isManaged(T entity) {
        return false;
    }

    @Override
    public void detach(T entity) {

    }

    @Override
    public LockModeType getLockMode(T entity) {
        return null;
    }

    @Override
    public void lock(T entity, LockModeType lockModeType) {

    }

    @Override
    public void clear() {

    }

}
