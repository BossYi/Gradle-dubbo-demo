package priv.yimeng.demo.service;

import priv.yimeng.demo.persistence.Filter;
import priv.yimeng.demo.persistence.Page;
import priv.yimeng.demo.persistence.Pageable;
import priv.yimeng.demo.persistence.domain.BaseDO;

import java.io.Serializable;
import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-02-01
 *
 * @author yimeng
 * @version 1.0
 */
public interface BaseService<T extends BaseDO<ID>, ID extends Serializable> {

    /**
     * 依据条件筛选集合
     *
     * @param filters 筛选
     * @return list
     */
    List<T> list(Filter... filters);

    /**
     * 查找实体对象集合
     *
     * @param querySelectName 搜索名：实体上构造函数的@QuerySelect的名称
     * @return 实体对象集合
     */
    List<T> list(String querySelectName);

    /**
     * 查询分页
     *
     * @param pageable 分页信息
     * @return 实体对象集合
     */
    Page<T> listPage(Pageable pageable);

}
