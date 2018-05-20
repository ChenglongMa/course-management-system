package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
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
    private final CourseService courseService;
    private final CourseOfferingService offeringService;
    private final EnrolmentService enrolmentService;
    private final ScheduleService scheduleService;
    private Schedule currSchedule;


    @Autowired
    public MainController(AdminService adminService,
                          CoordinatorService coordinatorService,
                          LecturerService lecturerService,
                          StudentService studentService,
                          CourseService courseService,
                          CourseOfferingService offeringService, EnrolmentService enrolmentService, ScheduleService scheduleService) {
        this.adminService = adminService;
        this.coordinatorService = coordinatorService;
        this.lecturerService = lecturerService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.offeringService = offeringService;
        this.enrolmentService = enrolmentService;
        this.scheduleService = scheduleService;

    }

    @GetMapping("/")
    public String index() {
        currSchedule = scheduleService.findCurrentSchedule();
        forTest();
        return "index";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam(name = "userId") String userId,
                              @RequestParam(name = "pwd") String pwd,
                              @RequestParam(name = "role") String role) {
        ModelAndView mav = new ModelAndView(role);
        try {
            User user = null, findUser = null;
            switch (role) {
                case "student":
                    user = new Student(userId, pwd);
                    findUser = studentService.findOne((Student) user);
                    break;
                case "admin":
                    user = new Admin(userId, pwd);
                    findUser = adminService.findOne((Admin) user);
                    break;
                case "coordinator":
                    user = new Coordinator(userId, pwd);
                    findUser = coordinatorService.findOne((Coordinator) user);
                    break;
                case "lecturer":
                    user = new Lecturer(userId, pwd);
                    findUser = lecturerService.findOne((Lecturer) user);
                    break;
            }
            if (user == null || !user.equals(findUser)) {
                mav.setViewName("/");
                mav.addObject("error", "noUser");
                return mav;
            }
            mav.addObject("user", findUser);
            return mav;
        } catch (Exception e) {
            return null;
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

    private void forTest() {
        Course ue = new Course();
        ue.setCode("c1002");
        ue.setName("UE");
        ue.setMainTopic("UE Main Topic");
        Course it = new Course();
        it.setCode("c1004");
        it.setName("ITIS");
        it.setMainTopic("ITIS Main Topic");
        Course pf = new Course();
        pf.setName("PF");
        pf.setCode("c0001");
        pf.setMainTopic("PF Main Topic");
        Course ap = new Course();
        ap.setCode("c0002");
        ap.setName("AP");
        ap.setMainTopic("AP Main Topic");
        ap.addPrerequisite(pf);
        Course sef = new Course();
        sef.setCode("c1001");
        sef.setName("SEF");
        sef.setMainTopic("SEF Main Topic");
        sef.addPrerequisite(pf, ap);
        Course aa = new Course();
        aa.setCode("c1003");
        aa.setName("AA");
        aa.setMainTopic("AA Main Topic");
        aa.addPrerequisite(pf);
        courseService.saveCourse(ue);
        courseService.saveCourse(it);
        courseService.saveCourse(pf);
        courseService.saveCourse(ap);
        courseService.saveCourse(sef);
        courseService.saveCourse(aa);
        CourseOffering ueOff = new CourseOffering(ue);
        CourseOffering itOff = new CourseOffering(it);
        itOff.setCapacity(120);
        CourseOffering pfOff = new CourseOffering(pf);
        CourseOffering apOff = new CourseOffering(ap);
        apOff.setCapacity(50);
        CourseOffering sefOff = new CourseOffering(sef);
        CourseOffering aaOff = new CourseOffering(aa);
        offeringService.save(ueOff);
        offeringService.save(itOff);
        offeringService.save(pfOff);
        offeringService.save(apOff);
        offeringService.save(sefOff);
        offeringService.save(aaOff);
        Schedule past = new Schedule(2016, 2);
        CourseOffering sefOffPast = new CourseOffering(sef, past);
        Schedule future = new Schedule(2020, 1);
        CourseOffering aaOffFut = new CourseOffering(aa, future);
        offeringService.save(sefOffPast);
        offeringService.save(aaOffFut);
        for (int i = 0; i < 10; i++) {
            Lecturer lecturer = new Lecturer("e2000" + i, "123");
            lecturerService.save(lecturer);
        }
    }
}
