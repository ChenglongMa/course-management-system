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

import java.util.Scanner;

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
    public static final Scanner scanner = new Scanner(System.in);
    private Schedule currSchedule;
    private final AdminController adminController;
    private Role role;


    @Autowired
    public MainController(AdminService adminService,
                          CoordinatorService coordinatorService,
                          LecturerService lecturerService,
                          StudentService studentService,
                          CourseService courseService,
                          CourseOfferingService offeringService, EnrolmentService enrolmentService, ScheduleService scheduleService, AdminController adminController, AdminController adminController1) {
        this.adminService = adminService;
        this.coordinatorService = coordinatorService;
        this.lecturerService = lecturerService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.offeringService = offeringService;
        this.enrolmentService = enrolmentService;
        this.scheduleService = scheduleService;
        this.adminController = adminController1;
    }

    private static void exit() {
        System.out.println("See you!");
        System.exit(0);
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
        courseService.save(ue);
        courseService.save(it);
        courseService.save(pf);
        courseService.save(ap);
        courseService.save(sef);
        courseService.save(aa);
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

    static void newPage() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    static String getMenu(String... items) {
        System.out.println("0. Exit.");
        for (int i = 0; i < items.length; i++) {
            System.out.println(i + 1 + ". " + items[i]);
        }
        String cmd = scanner.nextLine();
        if (cmd.equals("0")) {
            exit();
        }
        return cmd;
    }

    @GetMapping("/")
    public String index(String kwd) {
        currSchedule = scheduleService.findCurrentSchedule();
        forTest();
        if (kwd.equals("test")) {
            initial();
        }
        return "index";
    }

    public void initial() {
        try {
            newPage();
            System.out.println("Welcome to High 5 Course Management System.");
            while (true) {
                System.out.println("Please Select:");
                int item = Integer.parseInt(getMenu("Login.", "Sign Up."));
                if (item == 0) {
                    exit();
                }

                switch (item) {
                    case 1:
                        login();
                        break;
                    case 2:
                        signUp();
                        break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Oops! We have detected an issue.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    private String[] getProfile(boolean signUp) {
        String[] items = new String[4];
        System.out.println("Please enter your User ID:");
        System.out.print("User ID: ");
        items[0] = scanner.nextLine().trim();

        if (signUp) {
            System.out.println("Please enter your User Name: ");
            items[3] = scanner.nextLine().trim();
        }
        System.out.print("Please enter your Password:");
        items[1] = scanner.nextLine().trim();

        System.out.println("Please select your role:");
        items[2] = getMenu("Student", "Admin", "Coordinator", "Lecturer");
        return items;
    }

    private void login() throws Exception {
        String[] items = getProfile(false);
        String userId = items[0];
        String pwd = items[1];
        int item = Integer.parseInt(items[2]);
        User user = null, findUser = null;
        switch (item) {
            case 1:
                user = new Student(userId, pwd);
                findUser = studentService.findOne((Student) user);
                role = Role.Student;
                break;
            case 2:
                user = new Admin(userId, pwd);
                findUser = adminService.findOne((Admin) user);
                role = Role.Admin;
                break;
            case 3:
                user = new Coordinator(userId, pwd);
                findUser = coordinatorService.findOne((Coordinator) user);
                role = Role.Coordinator;
                break;
            case 4:
                user = new Lecturer(userId, pwd);
                findUser = lecturerService.findOne((Lecturer) user);
                role = Role.Lecturer;
                break;
        }
        if (user == null || !user.equals(findUser)) {
            throw new Exception("There is no such user.");
        }

        System.out.println("Login successfully! Welcome, " + findUser.getName());
        newPage();
        switch (role) {

            case Student:
                StudentController.init();
                break;
            case Admin:
                adminController.init();
                break;
            case Coordinator:
                CoordinatorController.init();
                break;
            case Lecturer:
                LecturerController.init();
                break;
        }
    }

    private void signUp() {
        String[] profile = getProfile(true);
        String userId = profile[0];
        String name = profile[3];
        String pwd = profile[1];
        int item = Integer.parseInt(profile[2]);
        User user;
        switch (item) {
            case 1:
                user = new Student(userId, pwd);
                user.setName(name);
                studentService.insert((Student) user);
                break;
            case 2:
                user = new Admin(userId, pwd);
                user.setName(name);
                adminService.insert((Admin) user);
                break;
            case 3:
                user = new Coordinator(userId, pwd);
                user.setName(name);
                coordinatorService.insert((Coordinator) user);
                break;
            case 4:
                user = new Lecturer(userId, pwd);
                user.setName(name);
                lecturerService.insert((Lecturer) user);
                break;
        }
        System.out.println("Sign up successfully! Welcome, " + name);
    }


    private enum Role {
        Student,
        Admin,
        Coordinator,
        Lecturer
    }
}
