package priv.yimeng.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.yimeng.demo.persistence.entity.UserDO;
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
    @RequestMapping("/list")
    public List<UserDO> list() {
        return userService.list();
    }

    /**
     * 查询所有学生
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody UserDO userDO) {
        userService.save(userDO);
    }

    @RequestMapping("/find/{name}/{age}")
    public UserDO findByNameAndAge(@PathVariable String name, @PathVariable Integer age) {
        return userService.findByNameAndAge(name, age);
    }

}
