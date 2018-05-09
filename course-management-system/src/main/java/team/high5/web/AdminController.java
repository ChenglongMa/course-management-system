package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.high5.domain.user.Admin;
import team.high5.service.AdminService;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : AdminController
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Admin> getAdmins() {
        return adminService.get();
    }

    @GetMapping("/create")
//    @PostMapping("/create")
    @ResponseBody
    public Admin postAdmin() {
        Admin a = new Admin();
        a.setUserId("e1001");
        a.setName("Tom");
        a.setPassword("123");
        return adminService.insert(a);
    }
}
