package priv.yimeng.demo.service;

import priv.yimeng.demo.persistence.domain.GoodInfoDO;

import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
public interface GoodInfoService {

    /**
     * 根据类型查找
     *
     * @param id typeId
     * @return goodInfo
     */
    List<GoodInfoDO> listByGoodTypeId(Long id);

}
