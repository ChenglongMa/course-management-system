package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Student;
import team.high5.service.CoordinatorService;
import team.high5.service.CourseService;
import team.high5.service.EnrolmentService;
import team.high5.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static team.high5.web.MainController.getSubMenu;
import static team.high5.web.MainController.scanner;

/**
 * course-management-system
 *
 * @Author : Chao Yan
 * @Date : 21-05-2018
 * @Time : 17:26
 * @Description :
 */
@Controller
public class CoordinatorController {
    private final CourseService courseService;
    private final StudentService studentService;
    private final CoordinatorService coordinatorService;
    private final EnrolmentService enrolmentService;
    List<Course> courseList = new ArrayList<>();

    @Autowired
    public CoordinatorController(CourseService courseService, StudentService studentService, CoordinatorService coordinatorService, EnrolmentService enrolmentService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.coordinatorService = coordinatorService;
        this.enrolmentService = enrolmentService;
    }

    public void init() {
        int cmd = getSubMenu("Add new course.", "Change the course detail", "Grant permission", "Grant exemption.");
        switch (cmd) {
            case 1:
                this.addCourse();
                break;
            case 2:
                this.changeCourseDetail();
                break;
            case 3:
                this.grantPermission();
                break;
            case 4:
                this.grantExemption();
                break;
        }

    }

    private void addCourse() {
        Course newCourse = new Course();
        newCourse.setElective(false);
        System.out.println();
        System.out.println("Please insert the course name.");
        newCourse.setName(scanner.next());
        System.out.println();
        System.out.println("Please insert the course code.");
        newCourse.setCode(scanner.next());
        System.out.println();
        courseService.addCourse(newCourse);
        this.init();
    }

    private void changeCourseDetail() {
        courseList = courseService.findAll();
        int i;
        System.out.println("Course List: ");
        for (i = 0; i < courseList.size(); i++) {
            System.out.println(1 + i + ". " + courseList.get(i).getName());
        }
        Course selectedCourse = courseList.get(scanner.nextInt() - 1);
        this.setCourseDetail(selectedCourse);
        System.out.println();
    }

    private void setCourseDetail(Course selectedCourse) {
        int cmd = getSubMenu("Set name.", "Set code.", "Set main topic.", "Set prerequisities.", "Set be elective or not.", "Back. ");
        switch (cmd) {
            case 1:
                this.setName(selectedCourse);
                break;
            case 2:
                this.setCode(selectedCourse);
                break;
            case 3:
                this.setMaintopic(selectedCourse);
                break;
            case 4:
                this.setPrerequisities(selectedCourse);
                break;
            case 5:
                this.setElective(selectedCourse);
                break;
            case 6:
                this.init();
        }
    }

    private void setName(Course selectedCourse) {
        System.out.println("Please insert the course name.");
        courseService.findCourseByCode(selectedCourse.getCode()).setName(scanner.next());
        courseService.save(selectedCourse);
        System.out.println();
        this.setCourseDetail(selectedCourse);
    }

    private void setCode(Course selectedCourse) {
        System.out.println("Please insert the course code.");
        courseService.findCourseByCode(selectedCourse.getCode()).setCode(scanner.next());
        courseService.save(selectedCourse);
        System.out.println();
        this.setCourseDetail(selectedCourse);
    }

    private void setMaintopic(Course selectedCourse) {
        System.out.println("Please insert the course main topic.");
        courseService.findCourseByCode(selectedCourse.getCode()).setMainTopic(scanner.next());
        courseService.save(selectedCourse);
        System.out.println();
        this.setCourseDetail(selectedCourse);
    }

    private void setElective(Course selectedCourse) {
        if (selectedCourse.isElective()) {
            System.out.println("This course is elective.");
        } else {
            System.out.println("This course is not elective");
        }
        System.out.println("Please choose this course to be elective or not.");
        System.out.println("1. Elective");
        System.out.println("2. Not Elective");
        System.out.println();
        if (scanner.nextInt() == 1) {
            courseService.findCourseByCode(selectedCourse.getCode()).setElective(true);
        }
        if (selectedCourse.isElective()) {
            System.out.println("Now, this course is elective.");
        } else {
            System.out.println("Now, this course is not elective");
        }
        this.init();
    }

    private void setPrerequisities(Course selectedCourse) {
        courseList = courseService.findAll();
        int i;
        this.printPrerequisities(selectedCourse);
        System.out.println();
        System.out.println("Please insert the index number of which course you want to add as a prerequisity.");
        System.out.println("Course List: ");
        for (i = 0; i < courseList.size(); i++) {
            if ((!courseList.get(i).equals(selectedCourse)) && (!selectedCourse.getPrerequisites().contains(courseList.get(i)))) {
                System.out.println(1 + i + ". " + courseList.get(i).getName());

            }
        }
        System.out.println();
        courseService.findCourseByCode(selectedCourse.getCode()).addPrerequisite(courseList.get(scanner.nextInt() - 1));
        courseService.save(selectedCourse);
        System.out.println();
        this.printPrerequisities(selectedCourse);
        System.out.println();
        this.setCourseDetail(selectedCourse);
    }

    private void grantPermission() {
        while (true) {
            try {
                System.out.println("Please insert the student id.");
                String studentCode = scanner.next();
                if (studentService.findOne(studentCode) == null) {
                    throw new IllegalArgumentException("Cannot find this student.");
                } else {
                    Student student = studentService.findOne(studentCode);
                    System.out.println();
                    System.out.println("The load number of this student is " + student.getMaxLoad());
                    System.out.println();
                    System.out.println("Please insert the max load number.");
                    int number = scanner.nextInt();
                    coordinatorService.grantPermission(student, number);
                    System.out.println();
                    System.out.println("The load number of this student is " + student.getMaxLoad());
                    System.out.println();
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.init();
    }


    private void grantExemption() {
        while (true) {
            try {
                System.out.println("Please insert the student id.");
                String studentCode = scanner.next();
                if (studentService.findOne(studentCode) == null) {
                    throw new IllegalArgumentException("Cannot find this student.");
                } else {
                    Student student = studentService.findOne(studentCode);
                    System.out.println();
                    this.printPerformance(student);
                    System.out.println();
                    while (true) {
                        try {
                            System.out.println("Please insert the course code for getting exemption.");
                            String courseCode = scanner.next();
                            Course course = courseService.findCourseByCode(courseCode);
                            boolean exist = false;
                            for (int i = 0; i < enrolmentService.findOffering(student).size(); i++) {
                                if (enrolmentService.findOffering(student).get(i).getCourse().equals(course)) {
                                    exist = true;
                                }
                            }
                            if (courseService.findCourseByCode(courseCode) == null || exist) {
                                throw new IllegalArgumentException("Cannot grant this course.");
                            } else {
                                coordinatorService.grantExemption(student, course);
                                this.printPerformance(student);
                                System.out.println();
                                break;
                            }
                        } catch (Exception e1) {
                            System.out.println(e1.getMessage());
                        }
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.init();
    }

    private void printPerformance(Student student) {
        List<Enrolment> enrolments = enrolmentService.findAllByStudent(student);
        if (enrolments != null && !enrolments.isEmpty()) {
            System.out.println("Performance Menu :");
            for (int i = 0; i < enrolments.size(); i++) {
                System.out.println(1 + i + " " + enrolments.get(i).getOffering().getCourse().getName());
            }
        } else {
            System.out.println("The student do not have any performance.");
        }
    }

    private void printPrerequisities(Course selectedCourse) {
        if (!selectedCourse.getPrerequisites().isEmpty()) {
            System.out.println("The prerequisities of this course :");
            for (int i = 0; i < selectedCourse.getPrerequisites().size(); i++) {
                System.out.println(1 + i + ". " + selectedCourse.getPrerequisites().get(i).getName());
            }
        } else {
            System.out.println("This course does not have any prerequisites.");
        }
    }
}

