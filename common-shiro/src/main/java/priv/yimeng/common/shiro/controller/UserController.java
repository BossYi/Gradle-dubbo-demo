package priv.yimeng.common.shiro.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import priv.yimeng.common.core.Page;
import priv.yimeng.common.core.Pageable;
import priv.yimeng.common.shiro.entity.UserDO;
import priv.yimeng.common.shiro.service.UserService;

import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询所有学生
     *
     * @return list
     */
    @RequestMapping("/list-all")
    public List<UserDO> list() {
        return userService.list();
    }

    @ApiOperation(value = "分页显示所有用户", notes = "分页显示")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Page<UserDO> listPage(@ApiParam Pageable pageable) {
        return userService.listPage(pageable);
    }

    /**
     * save学生
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody UserDO userDO) {
        userService.save(userDO);
    }

}
