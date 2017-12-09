package priv.yimeng.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.yimeng.demo.persistence.entity.UserDO;
import priv.yimeng.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
@Controller
@Slf4j
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String loginView() {

        log.debug("记录debug日志");
        log.info("访问了index方法");
        log.error("记录error错误日志");

        return "login";
    }

/*    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String login(String username, String password, HttpServletRequest request) {
        log.info(username + ": " + password);

        UserDO user = userService.findByNameAndPassword(username, password);
        if (user != null) {
            request.getSession(true).setAttribute("SESSION_USER", user);
            return "login success!";
        }

        return "username or password incorrect!";
    }*/

}


