package priv.yimeng.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@Api(value = "职位相关接口", tags = "职位接口")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "获取职位列表")
    public Page<Position> list(@ApiParam(required = true) Pageable pageable){
        return positionService.listPage(pageable);
    }

}
