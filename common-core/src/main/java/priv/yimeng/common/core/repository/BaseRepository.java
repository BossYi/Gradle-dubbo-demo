package priv.yimeng.common.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import priv.yimeng.common.core.Filter;
import priv.yimeng.common.core.Order;
import priv.yimeng.common.core.Page;
import priv.yimeng.common.core.Pageable;

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
     * 更新实体
     *
     * @param t 实体
     */
    void update(T t);

}
