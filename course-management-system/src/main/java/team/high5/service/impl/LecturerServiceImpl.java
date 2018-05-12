package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Lecturer;
import team.high5.domain.user.Student;
import team.high5.service.LecturerService;
import team.high5.service.ScheduleService;
import team.high5.service.StaffService;
import team.high5.service.StudentService;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : LecturerServiceImpl
 */
@Service
public class LecturerServiceImpl implements LecturerService {

    private final StudentService studentService;
    private final ScheduleService scheduleService;
    private final StaffService staffService;

    @Autowired
    public LecturerServiceImpl(StudentService studentService,
                               ScheduleService scheduleService,
                               @Qualifier("staffServiceImpl") StaffService staffService) {
        this.studentService = studentService;
        this.scheduleService = scheduleService;
        this.staffService = staffService;
    }

    @Override
    public boolean uploadResult(Lecturer lecturer, Student student, String result) throws NullPointerException {
        Student stu = studentService.findOne(student);
        List<Enrolment> enrolments = stu.getPerformance();
        Schedule currentSchedule = scheduleService.findCurrentSchedule();
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

    @Override
    public List<Enrolment> viewPastPerformance(Student student) {
        return staffService.viewPastPerformance(student);
    }
}
