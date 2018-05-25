package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Lecturer;
import team.high5.domain.user.Student;
import team.high5.repository.LecturerRepo;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : LecturerServiceImpl
 */
@Service
public class LecturerService extends StaffService<Lecturer> {

    private final StudentService studentService;
    private final ScheduleService scheduleService;
    private final LecturerRepo lecturerRepo;
    private final EnrolmentService enrolmentService;

    @Autowired
    public LecturerService(StudentService studentService,
                           ScheduleService scheduleService,
                           LecturerRepo lecturerRepo,
                           EnrolmentService enrolmentService, EnrolmentService enrolmentService1) {
        super(studentService, lecturerRepo, enrolmentService);
        this.studentService = studentService;
        this.scheduleService = scheduleService;
        this.lecturerRepo = lecturerRepo;
        this.enrolmentService = enrolmentService1;
    }

    public boolean uploadResult(Lecturer lecturer, Student student, String result) throws NullPointerException {
        Schedule currentSchedule = scheduleService.findCurrentSchedule();
        if (currentSchedule.getWeek() != 12) {
            throw new IllegalArgumentException("Cannot upload result until the end of the semester.");
        }
        Student stu = studentService.findOne(student);
        List<Enrolment> enrolments = stu.getPerformance();
        Enrolment enrolment = null;
        for (Enrolment e : enrolments) {
            CourseOffering offering = e.getOffering();
            if (offering.getLecturer().equals(lecturer) && offering.getSchedule().equals(currentSchedule)) {
                enrolment = e;
                break;
            }
        }
        if (enrolment == null) {
            throw new NullPointerException("There is no such student in the lecturer's course");
        }
        enrolment.setResult(result);
        studentService.save(student);
        return true;
    }

    public boolean uploadResult(Lecturer lecturer, CourseOffering offering, String userId, String result) {
        if (Schedule.currentSchedule().getWeek() != 12) {
            throw new IllegalArgumentException("Cannot upload result until the end of the semester.");
        }
        Student stu = studentService.findOne(userId);
        if (stu == null) {
            throw new IllegalArgumentException("There is no such student.");
        }
        if (!offering.getLecturer().equals(lecturer)) {
            throw new IllegalArgumentException("This offering doesn't belong to this lecturer.");
        }
        List<Enrolment> enrolments = enrolmentService.findAllByOfferingAndStudent(offering, stu);
        if (enrolments == null) {
            throw new IllegalArgumentException("Cannot find the enrolment of this student.");
        }
        for (Enrolment enrolment : enrolments) {
            if (enrolment.getOffering().equals(offering)) {
                enrolment.setResult(result);
                enrolmentService.save(enrolment);
                return true;
            }
        }
        throw new IllegalArgumentException("Cannot find such student in this offering");
    }

}
