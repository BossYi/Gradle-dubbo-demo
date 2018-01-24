package priv.yimeng.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
@Controller
@Slf4j
public class IndexController {

    @RequestMapping("/secure/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String home() {
        return "hello";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "/login";
    }
}


