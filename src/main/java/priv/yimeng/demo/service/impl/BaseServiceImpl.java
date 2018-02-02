package priv.yimeng.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import priv.yimeng.demo.persistence.Filter;
import priv.yimeng.demo.persistence.domain.BaseDO;
import priv.yimeng.demo.persistence.repository.BaseRepository;
import priv.yimeng.demo.service.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-02-01
 *
 * @author yimeng
 * @version 1.0
 */
@Transactional(rollbackFor = Exception.class)
public class BaseServiceImpl<T extends BaseDO<ID>, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    @Override
    public List<T> list(Filter... filters) {
        return baseRepository.list(filters);
    }

}
