package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Student;
import team.high5.repository.StudentRepo;
import team.high5.service.ScheduleService;
import team.high5.service.StudentService;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:04
 * @Description :
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final ScheduleService scheduleService;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, ScheduleService scheduleService) {
        this.studentRepo = studentRepo;
        this.scheduleService = scheduleService;
    }

    public Student findOne(String id) {
        return studentRepo.findById(id).get();
    }

    @Override
    public boolean enrol(Student student, CourseOffering offering) {
        boolean res = offering.getSchedule().equals(scheduleService.findCurrentSchedule());
        if (!res) {
            throw new IllegalArgumentException("The offering is not for this semester.");
        }
        // TODO: 2018/5/9 0009 To be finished.
        return false;
    }

}
