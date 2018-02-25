package priv.yimeng.common.core.service;

import priv.yimeng.common.core.Filter;
import priv.yimeng.common.core.Page;
import priv.yimeng.common.core.Pageable;
import priv.yimeng.common.core.entity.BaseDO;

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
     * 根据id获取实体
     *
     * @param id id
     * @return t
     */
    T get(ID id);

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

    /**
     * 保存实体
     *
     * @param t object
     * @return t
     */
    T save(T t);

}
