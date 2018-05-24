package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Student;
import team.high5.repository.StudentRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:04
 * @Description :
 */
@Service
public class StudentService extends UserService<Student> {

    private final StudentRepo studentRepo;
    private final ScheduleService scheduleService;
    private final CourseOfferingService offeringService;
    private final EnrolmentService enrolmentService;
    private final CourseService courseService;


    @Autowired
    public StudentService(StudentRepo studentRepo,
                          ScheduleService scheduleService,
                          CourseOfferingService offeringService, EnrolmentService enrolmentService, CourseService courseService) {
        super(studentRepo);
        this.studentRepo = studentRepo;
        this.scheduleService = scheduleService;
        this.offeringService = offeringService;
        this.enrolmentService = enrolmentService;
        this.courseService = courseService;
    }


    public boolean checkPrerequisites(Student student, CourseOffering offering) {
        List<Course> passedCourse = new ArrayList<>();

        for (int i = 0; i < student.getPerformance().size(); i++) {
            if (!student.getPerformance().get(i).getResult().equals("FAIL") && !student.getPerformance().get(i).getResult().equals("RNF"))
                passedCourse.add(student.getPerformance().get(i).getOffering().getCourse());
        }

        List<Course> prerequisites = offering.getCourse().getPrerequisites();
        for (Course course : prerequisites) {
            if (!passedCourse.contains(course)) {
                return false;
            }
        }
        return true;
    }


    public void checkEnrolCourse(Student student, CourseOffering offering) throws Exception {
        if (offering == null) {
            throw new Exception("There is no such Course Offering");
        }
        int CurrentLoad = 0;
        for (int i = 0; i < student.getPerformance().size(); i++) {
            if (student.getPerformance().get(i).getOffering().getSchedule().equals(Schedule.currentSchedule())) {
                CurrentLoad++;
            }
        }
        if (student.getMaxLoad() <= CurrentLoad)
            throw new Exception("You have already get your maximum load");
        List<Enrolment> enrolments = enrolmentService.getCurrEnrolments(offering);
        if (enrolments != null && !enrolments.isEmpty()) {
            if (offering.getCapacity() == enrolmentService.getCurrEnrolments(offering).size())
                throw new Exception(offering.getCourse().getName() + " is already full");
        }

        if (!checkPrerequisites(student, offering))
            throw new Exception("You have not get prerequisites of course " + offering.getCourse().getName());
        if (offering.getCourse().isElective()) {
            int CurrentElectives = 0;
            for (int j = 0; j < student.getPerformance().size(); j++) {
                CourseOffering offer = student.getPerformance().get(j).getOffering();
                if (offer.getCourse().isElective() && offer.getSchedule().equals(Schedule.currentSchedule())) {
                    CurrentElectives++;
                }
            }
            if (student.getMaxElectives() == CurrentElectives)
                throw new Exception("You have already get your maximum ElectivesCourse load");
        }
    }

    public void enrolCourse(Student student, String code) {
        try {
            Course course = courseService.findCourseByCode(code);
            if (course == null) {
                throw new Exception("Wrong course Code!");
            }
            CourseOffering offering = null;
            for (CourseOffering offer : offeringService.findOfferingsInCurrentSemester()) {
                if (offer.getCourse().equals(course)) {
                    offering = offer;
                }
            }
            checkEnrolCourse(student, offering);
            Enrolment enrolment = enrolmentService.enrolCourse(student, offering);
            student.addEnrolments(enrolment);
            save(student);
            System.out.println("You have successful enroled in course " + offering.getCourse().getName());

        } catch (Exception e) {
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public void viewCourseOffering(Student student) {
        List<CourseOffering> allCourseOffering = offeringService.findOfferingsInCurrentSemester();
        if (allCourseOffering == null) {
            System.out.println("There is no course offerings in system.");
            return;
        }

        System.out.println("Student " + student.getName() + " 's avaliable courseOffering:");
        for (CourseOffering offering : allCourseOffering) {
            List<Enrolment> enrolments = enrolmentService.getCurrEnrolments(offering);
            int left = offering.getCapacity();
            if (enrolments != null) {
                left -= enrolments.size();
            }
            if (checkPrerequisites(student, offering)) {
                System.out.println(offering + " left: " + left);
            }
        }
    }

}
