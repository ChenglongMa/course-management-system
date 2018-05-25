package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Lecturer;
import team.high5.service.CourseOfferingService;
import team.high5.service.LecturerService;
import team.high5.service.StudentService;

import java.util.List;

import static team.high5.web.MainController.getSubMenu;
import static team.high5.web.MainController.scanner;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 21-05-2018
 * @Time : 17:27
 * @Description :
 */
@Controller
public class LecturerController {
    private final LecturerService lecturerService;
    private final StudentService studentService;
    private final CourseOfferingService offeringService;
    private List<CourseOffering> offerings;

    @Autowired
    public LecturerController(LecturerService lecturerService, StudentService studentService, CourseOfferingService offeringService) {
        this.lecturerService = lecturerService;
        this.studentService = studentService;
        this.offeringService = offeringService;
    }

    void init(Lecturer lecturer) throws InterruptedException {
        while (true) {
            Thread.sleep(500);
            int cmd = getSubMenu("View Past Performance.", "Upload Result.");
            try {
                switch (cmd) {
                    case 1:
                        viewPerformance();
                        break;
                    case 2:
                        uploadResult(lecturer);
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

    private void uploadResult(Lecturer lecturer) throws Exception {
        System.out.println("Please enter the Student ID:");
        String userId = scanner.next();
        printAllOffering();
        System.out.println("Please select Course Offering you want to process");
        int index = scanner.nextInt() - 1;
        if (offerings.size() <= index) {
            throw new Exception("You tried to access an array out of bounds");
        }
        System.out.println("Please enter the result:");
        String result = scanner.next();
        if (lecturerService.uploadResult(lecturer, offerings.get(index), userId, result)) {
            System.out.println("You have upload the result.");
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

    private void viewPerformance() {
        System.out.println("Please enter the Student ID:");
        String userId = scanner.next();
        List<Enrolment> performance = lecturerService.viewPastPerformance(userId);
        if (performance.isEmpty()) {
            System.err.println("There is no enrolment for this student.");
            return;
        }
        for (Enrolment enrolment : performance) {
            System.out.println("Enrolment: " + enrolment);
        }
    }
}
