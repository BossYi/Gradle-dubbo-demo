package priv.yimeng.common.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import priv.yimeng.common.core.Filter;
import priv.yimeng.common.core.Page;
import priv.yimeng.common.core.Pageable;
import priv.yimeng.common.core.entity.BaseDO;
import priv.yimeng.common.core.repository.BaseRepository;
import priv.yimeng.common.core.service.BaseService;

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
    public T get(ID id) {
        return baseRepository.findOne(id);
    }

    @Override
    public List<T> list(Filter... filters) {
        return baseRepository.list(filters);
    }

    @Override
    public List<T> list(String querySelectName) {
        return baseRepository.list(querySelectName);
    }

    @Override
    public Page<T> listPage(Pageable pageable) {
        return baseRepository.listPage(pageable);
    }

    @Override
    public T save(T t) {
        return baseRepository.save(t);
    }
}
