package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Student;
import team.high5.service.StaffService;
import team.high5.service.StudentService;

import java.util.List;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 19:14
 * @Description :
 */
@Controller
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;
    private final StudentService studentService;

    @Autowired
    public StaffController(StaffService staffService, StudentService studentService) {
        this.staffService = staffService;
        this.studentService = studentService;
    }

    @GetMapping("/enr/{id}")
    @ResponseBody
    public List<Enrolment> getEnrolments(@PathVariable("id") String id) {
        Student student = studentService.findOne(id);
        return staffService.viewPastPerformance(student);
    }
}
