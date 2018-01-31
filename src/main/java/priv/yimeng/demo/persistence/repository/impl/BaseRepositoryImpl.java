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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-25
 *
 * @author yimeng
 * @version 1.0
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

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

    }

    private void addOrders(CriteriaQuery<T> criteriaQuery, List<Order> orders) {
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
