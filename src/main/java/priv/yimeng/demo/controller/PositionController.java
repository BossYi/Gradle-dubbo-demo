package priv.yimeng.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import priv.yimeng.demo.persistence.Page;
import priv.yimeng.demo.persistence.Pageable;
import priv.yimeng.demo.persistence.domain.Position;
import priv.yimeng.demo.service.PositionService;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-02-23
 */
@RequestMapping("/position")
@Controller
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping("/list")
    public Page<Position> list(Pageable pageable){
        return positionService.listPage(pageable);
    }

}
