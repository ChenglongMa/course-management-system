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

    @Autowired
    public LecturerService(StudentService studentService,
                           ScheduleService scheduleService,
                           LecturerRepo lecturerRepo,
                           EnrolmentService enrolmentService) {
        super(studentService, lecturerRepo, enrolmentService);
        this.studentService = studentService;
        this.scheduleService = scheduleService;
        this.lecturerRepo = lecturerRepo;
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

}
