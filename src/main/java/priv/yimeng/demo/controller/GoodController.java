package priv.yimeng.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.yimeng.demo.persistence.entity.GoodInfoDO;
import priv.yimeng.demo.service.GoodInfoService;

import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@RestController
@RequestMapping("/good")
public class GoodController {
    private final GoodInfoService goodInfoService;

    @Autowired
    public GoodController(GoodInfoService goodInfoService) {
        this.goodInfoService = goodInfoService;
    }

    @RequestMapping("/good_type/{id}")
    public List<GoodInfoDO> listByGoodTypeId(@PathVariable Long id) {
        return goodInfoService.listByGoodTypeId(id);
    }

}
