package priv.yimeng.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.yimeng.demo.persistence.Page;
import priv.yimeng.demo.persistence.Pageable;
import priv.yimeng.demo.persistence.domain.GoodInfoDO;
import priv.yimeng.demo.service.GoodInfoService;

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

    @Autowired
    private GoodInfoService goodInfoService;

    @RequestMapping("/good_type/{id}")
    public GoodInfoDO listByGoodTypeId(@PathVariable Long id) {
        return goodInfoService.get(id);
    }

    @RequestMapping("/list")
    public Page<GoodInfoDO> listPage() {
        return goodInfoService.listPage(new Pageable());
    }
}
