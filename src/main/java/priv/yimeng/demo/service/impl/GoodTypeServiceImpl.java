package priv.yimeng.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.yimeng.demo.persistence.entity.GoodTypeDO;
import priv.yimeng.demo.persistence.repository.GoodTypeRepository;
import priv.yimeng.demo.service.GoodTypeService;

/**
 * Desc:
 * Author:  yimeng
 * Date:    2017-11-28
 * Time:    22:55
 */
@Service
public class GoodTypeServiceImpl implements GoodTypeService {

    @Autowired
    private GoodTypeRepository goodTypeRepository;

    @Override
    public GoodTypeDO findOne(Long id) {
        return goodTypeRepository.findOne(id);
    }
}
