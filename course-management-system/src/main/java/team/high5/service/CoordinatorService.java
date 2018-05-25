package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Coordinator;
import team.high5.domain.user.Student;
import team.high5.repository.CoordinatorRepo;
import team.high5.repository.EnrolmentRepo;
import team.high5.repository.StudentRepo;

/**
 * course-management-system
 *
 * @Author : Chao Yan
 * @Date : 09-05-2018
 * @Time : 15:02
 * @Description :
 */
@Service
public class CoordinatorService extends UserService<Coordinator> {

    private final CourseService courseService;
    private final StudentService studentService;
    private final CoordinatorRepo coordinatorRepo;
    private final EnrolmentService enrolmentService;
    private final StudentRepo studentRepo;
    private final EnrolmentRepo enrolmentRepo;
    private final CourseOfferingService courseOfferingService;


    @Autowired
    public CoordinatorService(CourseService courseService, StudentService studentService, CoordinatorRepo coordinatorRepo, EnrolmentService enrolmentService, StudentRepo studentRepo, EnrolmentRepo enrolmentRepo, CourseOfferingService courseOfferingService) {
        super(coordinatorRepo);
        this.courseService = courseService;
        this.studentService = studentService;
        this.coordinatorRepo = coordinatorRepo;
        this.enrolmentService = enrolmentService;
        this.studentRepo = studentRepo;
        this.enrolmentRepo = enrolmentRepo;
        this.courseOfferingService = courseOfferingService;
    }

    public Course addCourse(Course course) {
        return courseService.addCourse(course);
    }

    public void grantPermission(Student student, int maxLoad) {
        Student stu = studentService.findOne(student);
        if (stu == null) {
            throw new IllegalArgumentException("There is no such student.");
        }
        stu.setMaxLoad(maxLoad);
        studentService.save(stu);
    }

    public void grantExemption(Student student, Course course) {
        CourseOffering offering = new CourseOffering();
        offering.setCourse(course);
        offering.setSemester(Schedule.currentSchedule().getSemester());
        offering.setYear(Schedule.currentSchedule().getYear());
        courseOfferingService.save(offering);
        Enrolment enrolment = new Enrolment();
        enrolment.setOffering(offering);
        enrolment.setStudent(student);
        enrolmentService.save(enrolment);
        studentService.findOne(student).addEnrolments(enrolment);
        studentService.save(student);
    }
}
