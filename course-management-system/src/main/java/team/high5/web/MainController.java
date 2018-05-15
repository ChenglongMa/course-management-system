package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.high5.domain.user.*;
import team.high5.service.*;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : MainController
 */
@Controller
public class MainController {
    private final AdminService adminService;
    private final CoordinatorService coordinatorService;
    private final LecturerService lecturerService;
    private final StudentService studentService;

    @Autowired
    public MainController(AdminService adminService,
                          CoordinatorService coordinatorService,
                          LecturerService lecturerService,
                          StudentService studentService) {
        this.adminService = adminService;
        this.coordinatorService = coordinatorService;
        this.lecturerService = lecturerService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
//    @ResponseBody
    public String login(@RequestParam(name = "userId") String userId,
                         @RequestParam(name = "pwd") String pwd,
                         @RequestParam(name = "role") String role) {
        try {
            User user = null, findUser = null;
            switch (role) {
                case "s":
                    user = new Student(userId, pwd);
                    findUser = studentService.findOne((Student) user);
                    break;
                case "a":
                    user = new Admin(userId, pwd);
                    findUser = adminService.findOne((Admin) user);
                    break;
                case "c":
                    user = new Coordinator(userId, pwd);
                    findUser = coordinatorService.findOne((Coordinator) user);
                    break;
                case "l":
                    user = new Lecturer(userId, pwd);
                    findUser = lecturerService.findOne((Lecturer) user);
                    break;
            }
            if (user == null || !user.equals(findUser)) {
                return "redirect:/";
            }
            return "redirect:/" + role;
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    @PostMapping("/signUp")
    @ResponseBody
    public boolean signUp(@RequestParam(name = "userId") String userId,
                          @RequestParam(name = "pwd") String pwd,
                          @RequestParam(name = "role") String role) {
        try {
            User user = null, findUser = null;
            switch (role) {
                case "s":
                    user = new Student(userId, pwd);
                    findUser = studentService.insert((Student) user);
                    break;
                case "a":
                    user = new Admin(userId, pwd);
                    findUser = adminService.insert((Admin) user);
                    break;
                case "c":
                    user = new Coordinator(userId, pwd);
                    findUser = coordinatorService.insert((Coordinator) user);
                    break;
                case "l":
                    user = new Lecturer(userId, pwd);
                    findUser = lecturerService.insert((Lecturer) user);
                    break;
            }
            if (user == null || findUser == null) {
                return false;
            }
            return user.equals(findUser);
        } catch (Exception e) {
            return false;
        }
    }
}
