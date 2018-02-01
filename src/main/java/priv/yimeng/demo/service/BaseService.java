package priv.yimeng.demo.service;

import priv.yimeng.demo.persistence.Filter;
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

}
