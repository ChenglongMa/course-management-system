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
import team.high5.domain.entities.Enrolment;
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
    public static final Scanner scanner = new Scanner(System.in);
    private final AdminService adminService;
    private final CoordinatorService coordinatorService;
    private final LecturerService lecturerService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final CourseOfferingService offeringService;
    private final EnrolmentService enrolmentService;
    private final ScheduleService scheduleService;
    private final AdminController adminController;
    private final CoordinatorController coordinatorController;
    private final LecturerController lecturerController;
    private final StudentController studentController;
    private Schedule currSchedule;
    private Role role;


    @Autowired
    public MainController(AdminService adminService,
                          CoordinatorService coordinatorService,
                          LecturerService lecturerService,
                          StudentService studentService,
                          CourseService courseService,
                          CourseOfferingService offeringService,
                          EnrolmentService enrolmentService,
                          ScheduleService scheduleService,
                          AdminController adminController,
                          CoordinatorController coordinatorController,
                          LecturerController lecturerController,
                          StudentController studentController) {
        this.adminService = adminService;
        this.coordinatorService = coordinatorService;
        this.lecturerService = lecturerService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.offeringService = offeringService;
        this.enrolmentService = enrolmentService;
        this.scheduleService = scheduleService;
        this.adminController = adminController;
        this.coordinatorController = coordinatorController;
        this.lecturerController = lecturerController;
        this.studentController = studentController;
    }

    static int getSubMenu(String... items) {
        System.out.println();
        System.out.println("0. Log Out.");
        for (int i = 0; i < items.length; i++) {
            System.out.println(i + 1 + ". " + items[i]);
        }
        return MainController.scanner.nextInt();
    }

    static void newPage() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }

    private void exit() {
        System.out.println("See you!");
        Schedule currSchedule = new Schedule(2018, 1);
        Schedule.setCurrentSchedule(currSchedule);
        scheduleService.saveCurrentSchedule();
        System.exit(0);
    }

    private int getMainMenu(String... items) {
        System.out.println();
        System.out.println("0. Exit.");
        for (int i = 0; i < items.length; i++) {
            System.out.println(i + 1 + ". " + items[i]);
        }
        int cmd = scanner.nextInt();
        if (cmd == 0) {
            exit();
        }
        return cmd;
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
        sefOff = offeringService.save(sefOff);
        offeringService.save(aaOff);
        Schedule past = new Schedule(2016, 2);
        CourseOffering sefOffPast = new CourseOffering(sef, past);
        Schedule future = new Schedule(2020, 1);
        CourseOffering aaOffFut = new CourseOffering(aa, future);
        sefOffPast = offeringService.save(sefOffPast);
        offeringService.save(aaOffFut);
        for (int i = 0; i < 10; i++) {
            Lecturer lecturer = new Lecturer("e2000" + i, "123");
            lecturer.setName("Lec 00" + i + 1);
            lecturerService.save(lecturer);
        }
        Student stu = new Student("s000", "123");
        stu.setName("Jerry");
        Student s = studentService.save(stu);
        Enrolment enrol = new Enrolment(s, sefOff);
        enrolmentService.save(enrol);
        Student sPast = new Student("p000", "123");
        sPast.setName("Past");
        sPast = studentService.save(sPast);
        Enrolment enPast = new Enrolment(sPast, sefOffPast);
        enrolmentService.save(enPast);
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    public void test() {
        currSchedule = scheduleService.findCurrentSchedule();
        forTest();
        initial();
    }

    public void initial() {
        while (true) {
            try {
                Thread.sleep(500);
                newPage();
                System.out.println("Welcome to High 5 Course Management System.");
                System.out.println("Please Select:");
                int item = getMainMenu("Login.", "Sign Up.");

                switch (item) {
                    case 1:
                        login();
                        break;
                    case 2:
                        signUp();
                        break;
                }
            } catch (Exception ex) {
                System.err.println("Oops! We have detected an issue.");
                System.err.println(ex.getMessage());
                System.err.println("Please try again.");
            }
        }
    }

    private String[] getProfile(boolean signUp) {
        String[] items = new String[4];
        System.out.println("Please enter your User ID:");
        System.out.print("User ID: ");
        items[0] = scanner.next().trim();

        if (signUp) {
            System.out.println("Please enter your User Name: ");
            items[3] = scanner.next().trim();
        }
        System.out.print("Please enter your Password:");
        items[1] = scanner.next().trim();

        System.out.println("Please select your role:");
        items[2] = String.valueOf(getMainMenu("Student", "Admin", "Coordinator", "Lecturer"));
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
            throw new Exception("Incorrect username or password.");
        }

        System.out.println("Login successfully! Welcome, " + findUser.getName());
        newPage();
        switch (role) {

            case Student:
                studentController.init((Student) findUser);
                break;
            case Admin:
                adminController.init();
                break;
            case Coordinator:
                coordinatorController.init();
                break;
            case Lecturer:
                lecturerController.init();
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
