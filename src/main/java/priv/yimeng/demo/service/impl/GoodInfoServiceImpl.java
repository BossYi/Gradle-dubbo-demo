package priv.yimeng.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.yimeng.demo.persistence.entity.GoodInfoDO;
import priv.yimeng.demo.persistence.entity.GoodTypeDO;
import priv.yimeng.demo.persistence.repository.GoodInfoRepository;
import priv.yimeng.demo.service.GoodInfoService;

import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@Service
public class GoodInfoServiceImpl implements GoodInfoService {

    private final GoodInfoRepository goodInfoRepository;

    @Autowired
    public GoodInfoServiceImpl(GoodInfoRepository goodInfoRepository) {
        this.goodInfoRepository = goodInfoRepository;
    }

    @Override
    public List<GoodInfoDO> listByGoodTypeId(Long id) {
        return goodInfoRepository.findByGoodTypeId(id);
    }

}
