package team.high5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : MainController
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
