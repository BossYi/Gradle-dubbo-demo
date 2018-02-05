package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import priv.yimeng.demo.persistence.Filter;
import priv.yimeng.demo.persistence.Order;
import priv.yimeng.demo.persistence.Page;
import priv.yimeng.demo.persistence.Pageable;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-01-24
 */
@NoRepositoryBean
@Transactional(readOnly = true, rollbackFor = Exception.class)
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    T getOneObject(ID username);

    /**
     * 查找实体对象集合
     *
     * @param filters 条件
     * @return 实体集合
     */
    List<T> list(Filter... filters);

    /**
     * 查找实体对象集合
     *
     * @param first   起始记录
     * @param count   数量
     * @param filters 筛选
     * @param orders  排序
     * @return 实体集合
     */
    List<T> list(Integer first, Integer count, List<Filter> filters, List<Order> orders);

    /**
     * 查找实体对象集合
     *
     * @param querySelectName 搜索名：实体上构造函数的@QuerySelect的名称
     * @return 实体对象集合
     */
    List<T> list(String querySelectName);

    /**
     * 查找实体对象集合
     *
     * @param querySelectName 搜索名：实体上构造函数的@QuerySelect的名称
     * @param first           起始记录
     * @param count           数量
     * @param filters         筛选
     * @param orders          排序
     * @return 实体对象集合
     */
    List<T> list(String querySelectName, Integer first, Integer count, List<Filter> filters, List<Order> orders);

    /**
     * 查找实体对象分页
     *
     * @param pageable 分页信息
     * @return 实体对象分页
     */
    Page<T> listPage(Pageable pageable);

    /**
     * 查找实体对象分页
     *
     * @param criteriaQuery criteriaQuery
     * @param pageable      分页信息
     * @return 实体对象分页
     */
    Page<T> listPage(CriteriaQuery<T> criteriaQuery, Pageable pageable);

    /**
     * 根据过滤统计
     *
     * @param filters 筛选
     * @return 统计数量
     */
    Long count(Filter... filters);

    /**
     * 持久化实体对象
     *
     * @param entity 实体对象
     */
    void persist(T entity);

    /**
     * 合并实体对象
     *
     * @param entity 实体对象
     * @return 实体对象
     */
    T merge(T entity);

    /**
     * 移除实体对象
     *
     * @param entity 实体对象
     */
    void remove(T entity);

    /**
     * 刷新实体对象
     *
     * @param entity 实体对象
     */
    void refresh(T entity);

    /**
     * 刷新实体对象
     *
     * @param entity       实体对象
     * @param lockModeType 锁定方式
     */
    void refresh(T entity, LockModeType lockModeType);

    /**
     * 获取实体对象ID
     *
     * @param entity 实体对象
     * @return 实体对象ID
     */
    ID getIdentifier(T entity);

    /**
     * 判断是否已加载
     *
     * @param entity 实体对象
     * @return 是否已加载
     */
    boolean isLoaded(T entity);

    /**
     * 判断属性是否已加载
     *
     * @param entity        实体对象
     * @param attributeName 属性名
     * @return 是否已加载
     */
    boolean isLoaded(T entity, String attributeName);

    /**
     * 判断是否为托管状态
     *
     * @param entity 实体对象
     * @return 是否为托管状态
     */
    boolean isManaged(T entity);

    /**
     * 设置为游离状态
     *
     * @param entity 实体对象
     */
    void detach(T entity);

    /**
     * 获取锁定方式
     *
     * @param entity 实体对象
     * @return 锁定方式
     */
    LockModeType getLockMode(T entity);

    /**
     * 锁定实体对象
     *
     * @param entity       实体对象
     * @param lockModeType 锁定方式
     */
    void lock(T entity, LockModeType lockModeType);

    /**
     * 清除缓存
     * <pre>
     * 全部整个jpa环境
     * </pre>
     */
    void clear();

}
