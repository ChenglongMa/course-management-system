package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Admin;
import team.high5.domain.user.Lecturer;
import team.high5.service.*;

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
    private final CourseService courseService;
    private final LecturerService lecturerService;
    List<CourseOffering> offerings = new ArrayList<>();
    List<Course> courses = new ArrayList<>();
    List<Lecturer> lecturers = new ArrayList<>();

    @Autowired
    public AdminController(AdminService adminService, EnrolmentService enrolmentService, CourseOfferingService offeringService, CourseService courseService, LecturerService lecturerService) {
        this.adminService = adminService;
        this.enrolmentService = enrolmentService;
        this.offeringService = offeringService;
        this.courseService = courseService;
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public String initial() {
        return "admin";
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

//    @GetMapping("/advance")
//    @ResponseBody
//    public Schedule advanceSystem() {
//        if (enrolmentService.existRNF()) {
//            System.out.println("存在结果为RNF");
//
//        }
//        adminService.advanceSystem();
//        return Schedule.currentSchedule();
//    }

    public void init() throws InterruptedException {
        while (true) {
            Thread.sleep(500);
            int cmd = MainController.getSubMenu("View Course Offerings.", "Add new Course Offering.", "Assign Lecturer.", "Advance System.");
            try {
                switch (cmd) {
                    case 1:
                        printAllOffering();
                        break;
                    case 2:
                        addCourseOffering();
                        break;
                    case 3:
                        assignLecturer();
                        break;
                    case 4:
                        advanceSystem();
                        break;
                    default:
                        return;
                }
            } catch (Exception ex) {
                System.err.println("From Admin: Oops! We have detected an issue.");
                System.err.println(ex.getMessage());
                System.err.println("Please try again.");
            }
        }


    }

    private void printAllOffering() throws Exception {
        offerings = offeringService.findAll();
        for (int i = 0; i < offerings.size(); i++) {
            System.out.println(1 + i + ". " + offerings.get(i));
        }
        if (offerings == null || offerings.isEmpty()) {
            throw new Exception("There is no available Offering.");
        }
    }

    private void printAllCourse() throws Exception {
        courses = courseService.findAll();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(1 + i + ". " + courses.get(i));
        }
        if (courses == null || courses.isEmpty()) {
            throw new Exception("There is no available Course.");
        }
    }

    private void printAllLecturer() throws Exception {
        lecturers = lecturerService.findAll();
        for (int i = 0; i < lecturers.size(); i++) {
            System.out.println(1 + i + ". " + lecturers.get(i));
        }
        if (lecturers == null || lecturers.isEmpty()) {
            throw new Exception("There is no available Lecturer.");
        }
    }

    private void addCourseOffering() throws Exception {
        printAllCourse();
        System.out.println("Please select Course you want to process");
        int index = MainController.scanner.nextInt() - 1;
        if (courses.size() <= index) {
            throw new Exception("You tried to access an array out of bounds");
        }
        Course course = courses.get(index);
        CourseOffering offering = new CourseOffering(course);
        System.out.println("Please specify the year of the offering:");
        offering.setYear(MainController.scanner.nextInt());
        System.out.println("Then the semester:");
        offering.setSemester(MainController.scanner.nextInt());
        System.out.println("Please specify the capacity of the offering:");
        offering.setCapacity(MainController.scanner.nextInt());
        adminService.addCourseOffering(offering);
        if (!offering.getSchedule().equals(Schedule.currentSchedule())) {
            return;
        }
        System.out.println("Do you want to assign a lecturer to this offering now? (y/n)");
        String res = MainController.scanner.next().toLowerCase();
        if (res.equals("y")) {
            assignLecturer(offering);
        }
    }

    private void assignLecturer(CourseOffering offering) throws Exception {
        if (offering.getLecturer() != null) {
            System.out.println("The Course Offering has had lecturer.");
            System.out.println("Do you want to continue? (y/n)");
            String cmd = MainController.scanner.next().toLowerCase();
            if (cmd.equals("n")) {
                return;
            }
        }
        printAllLecturer();
        System.out.println("Please select Lecturer.");
        int index = MainController.scanner.nextInt() - 1;
        if (lecturers.size() <= index) {
            throw new Exception("You tried to access an array out of bounds");
        }
        Lecturer lecturer = lecturers.get(index);
        adminService.assignLecturer(offering, lecturer);
        System.out.printf("You have assign the lecturer[%s] to the offering[%s]\n", lecturer.getName(), offering.getCourse().getName());
    }

    private void assignLecturer() throws Exception {
        printAllOffering();
        System.out.println("Please select Course Offering you want to process");
        int index = MainController.scanner.nextInt() - 1;
        if (offerings.size() <= index) {
            throw new Exception("You tried to access an array out of bounds");
        }
        assignLecturer(offerings.get(index));
    }

    private void advanceSystem() {
        System.out.println("Hold on, please.");
        if (enrolmentService.existRNF()) {
            System.err.println("There are some students with OUTSTANDING results which would be marked as \"RNF\".");
            System.err.println("Please Confirm (y/n):");
            String cmd = MainController.scanner.next().toLowerCase();
            if (cmd.equals("n")) {
                return;
            }
            adminService.markRNF();
        }
        adminService.advanceSystem();
        System.out.println("Current Schedule: " + Schedule.currentSchedule());
    }
}
