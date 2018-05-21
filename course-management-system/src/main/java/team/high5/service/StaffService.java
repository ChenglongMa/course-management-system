package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Student;
import team.high5.domain.user.User;
import team.high5.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : StaffServiceImpl
 */
@Service
public class StaffService<T extends User> extends UserService<T> {
    private final StudentService studentService;

    @Autowired
    public StaffService(StudentService studentService, UserRepo<T> userRepo) {
        super(userRepo);
        this.studentService = studentService;
    }

    public List<Enrolment> viewPastPerformance(Student student) {
        Student stu = studentService.findOne(student);
        if (stu == null) {
            throw new IllegalArgumentException("There is no such student.");
        }
        List<Enrolment> enrolments = stu.getPerformance();
        List<Enrolment> past = new ArrayList<>();
        for (Enrolment enrolment : enrolments) {
            if (enrolment.getOffering().getSchedule().compareTo(Schedule.currentSchedule()) < 0) {
                past.add(enrolment);
            }
        }
        return past;
    }

}
