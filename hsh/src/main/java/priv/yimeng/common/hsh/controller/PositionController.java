package priv.yimeng.common.hsh.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.yimeng.common.core.Page;
import priv.yimeng.common.core.Pageable;
import priv.yimeng.common.hsh.entity.Position;
import priv.yimeng.common.hsh.service.PositionService;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-02-23
 */
@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @ApiOperation(value = "list", tags = "职位list")
    @GetMapping("/list")
    public Page<Position> listPage(@ApiParam Pageable pageable) {
        return positionService.listPage(pageable);
    }

}
