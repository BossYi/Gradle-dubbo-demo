package priv.yimeng.demo.service;

import priv.yimeng.demo.persistence.domain.LogDO;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
public interface LogService {

    /**
     * save
     *
     * @param logDO logDO
     */
    void save(LogDO logDO);

}
