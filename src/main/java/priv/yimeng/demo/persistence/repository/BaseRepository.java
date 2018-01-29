package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import priv.yimeng.demo.persistence.Filter;
import priv.yimeng.demo.persistence.Order;

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
     * 持久化
     */
    void persist(T t);

    T getOneObject(ID username);

    /**
     * 依据filter查询实体集合
     *
     * @param filters 条件
     * @return 实体集合
     */
    List<T> list(Filter... filters);

    /**
     * 依据filter查询实体集合
     *
     * @param first   起始记录
     * @param count   数量
     * @param filters 筛选
     * @param orders  排序
     * @return 实体集合
     */
    List<T> list(Integer first, Integer count, List<Filter> filters, List<Order> orders);

}
