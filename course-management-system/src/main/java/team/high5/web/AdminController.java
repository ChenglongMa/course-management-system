package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Admin;
import team.high5.service.AdminService;
import team.high5.service.CourseOfferingService;
import team.high5.service.EnrolmentService;

import java.util.ArrayList;
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
    private final EnrolmentService enrolmentService;
    private final CourseOfferingService offeringService;
    List<CourseOffering> offerings = new ArrayList<>();

    @GetMapping
    public String initial() {
        return "admin";
    }

    @Autowired
    public AdminController(AdminService adminService, EnrolmentService enrolmentService, CourseOfferingService offeringService) {
        this.adminService = adminService;
        this.enrolmentService = enrolmentService;
        this.offeringService = offeringService;
    }

    @GetMapping("/create")
    @ResponseBody
    public Admin postAdmin() {
        Admin a = new Admin("e10023", "123");
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

    public void init() {
        int cmd = Integer.parseInt(MainController.getMenu("View Course Offerings.", "Add new Course Offering", "Assign Lecturer.", "Advance System"));
        switch (cmd) {
            case 1:
                printAllOffering();
                break;
            case 2:
                break;
            case 3:
                assignLecturer();
                break;
            case 4:
                advanceSystem();
                break;
        }
    }

    private void printAllOffering() {
        offerings = offeringService.findAll();
        for (int i = 0; i < offerings.size(); i++) {
            System.out.println(1 + i + ". " + offerings.get(i));
        }
    }

    private void addCourseOffering() {

    }

    private void assignLecturer() {
        if (offerings.isEmpty()) {
            printAllOffering();
        }
        int index;
        while (true) {
            System.out.println("Please select Course Offering you want to process");
            index = MainController.scanner.nextInt() - 1;
            if (offerings.size() <= index) {
                System.err.println("You tried to access an array out of bounds");
                System.err.println("Please try again.");
            }
        }
    }
}
