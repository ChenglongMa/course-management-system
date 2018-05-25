package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Lecturer;
import team.high5.domain.user.Student;
import team.high5.service.CourseOfferingService;
import team.high5.service.EnrolmentService;
import team.high5.service.LecturerService;
import team.high5.service.StudentService;

import java.util.ArrayList;
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
    private final EnrolmentService enrolmentService;
    private List<Enrolment> enrolmentList;
    private Lecturer lecturer;

    @Autowired
    public LecturerController(LecturerService lecturerService, StudentService studentService, CourseOfferingService offeringService, EnrolmentService enrolmentService) {
        this.lecturerService = lecturerService;
        this.studentService = studentService;
        this.offeringService = offeringService;
        this.enrolmentService = enrolmentService;
    }

    void init(Lecturer lecturer) throws InterruptedException {
        this.lecturer = lecturer;
        while (true) {
            Thread.sleep(500);
            int cmd = getSubMenu("View Past Performance.", "Upload Result.");
            try {
                switch (cmd) {
                    case 1:
                        viewPerformance();
                        break;
                    case 2:
                        uploadResult();
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

    private void uploadResult() throws Exception {
        if (Schedule.currentSchedule().getWeek() != 12) {
            throw new IllegalArgumentException("Cannot upload result until the end of the semester.");
        }
        System.out.println("Please enter the Student ID:");
        String userId = scanner.next();
        Student stu = studentService.findOne(userId);
        if (stu == null) {
            throw new IllegalArgumentException("There is no such student.");
        }
        printAllEnrolments(stu);
        System.out.println("Please select Enrolment you want to process");
        int index = scanner.nextInt() - 1;
        if (enrolmentList.size() <= index) {
            throw new Exception("You tried to access an array out of bounds");
        }
        System.out.println("Please enter the result:");
        String result = scanner.next();
        lecturerService.uploadResult(enrolmentList.get(index), result);
        System.out.println("You have upload the result.");

    }

    private void printAllEnrolments(Student student) throws Exception {
        enrolmentList = new ArrayList<>();
        for (CourseOffering offering : offeringService.findOfferingsInCurrentSemester()) {
            if (offering.getLecturer() != null && offering.getLecturer().equals(lecturer)) {
                List<Enrolment> enrolments = enrolmentService.findAllByOfferingAndStudent(offering, student);
                if (enrolments != null && !enrolments.isEmpty()) {
                    enrolmentList.addAll(enrolments);
                }
            }
        }
        for (int i = 0; i < enrolmentList.size(); i++) {
            System.out.println(1 + i + ". " + enrolmentList.get(i));
        }
        if (enrolmentList == null || enrolmentList.isEmpty()) {
            throw new Exception("There is no available Enrolment.");
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
