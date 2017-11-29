package priv.yimeng.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.yimeng.demo.persistence.entity.LogDO;
import priv.yimeng.demo.persistence.repository.LogRepository;
import priv.yimeng.demo.service.LogService;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void save(LogDO logDO) {
        logRepository.save(logDO);
    }
}
