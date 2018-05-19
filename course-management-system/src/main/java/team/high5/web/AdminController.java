package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Admin;
import team.high5.service.AdminService;
import team.high5.service.EnrolmentService;

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
    private final EnrolmentService enrolmentService;

    @Autowired
    public AdminController(AdminService adminService, EnrolmentService enrolmentService) {
        this.adminService = adminService;
        this.enrolmentService = enrolmentService;
    }

    @GetMapping
    public String getAdmins() {
        return "admin";
    }

    @GetMapping("/create")
//    @PostMapping("/create")
    @ResponseBody
    public Admin postAdmin() {
        Admin a = new Admin("e10023","123");
        a.setUserId("e10023");
        a.setName("Tom");
        a.setPassword("123");
        return adminService.save(a);
    }

    @GetMapping("/advance")
    @ResponseBody
    public Schedule advanceSystem() {
        if (enrolmentService.existRNF()) {
            System.out.println("存在结果为RNF");
            
        }
        adminService.advanceSystem();
        return Schedule.currentSchedule();
    }
}
