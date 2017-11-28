package priv.yimeng.demo.service;

import priv.yimeng.demo.persistence.entity.GoodTypeDO;

/**
 * Desc:
 * Author:  yimeng
 * Date:    2017-11-28
 * Time:    22:54
 */
public interface GoodTypeService {

    /**
     * 查询单个goodType
     *
     * @param id id
     * @return goodType
     */
    GoodTypeDO findOne(Long id);
}
