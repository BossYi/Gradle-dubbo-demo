package priv.yimeng.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import priv.yimeng.demo.persistence.Page;
import priv.yimeng.demo.persistence.Pageable;
import priv.yimeng.demo.persistence.domain.UserDO;
import priv.yimeng.demo.service.UserService;

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

    @RequestMapping("/list")
    public Page<UserDO> listPage(Pageable pageable) {
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
