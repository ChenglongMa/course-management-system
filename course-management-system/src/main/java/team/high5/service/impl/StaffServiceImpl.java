package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Student;
import team.high5.repository.EnrolmentRepo;
import team.high5.repository.StudentRepo;
import team.high5.service.StaffService;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : StaffServiceImpl
 */
@Service
public class StaffServiceImpl implements StaffService {
    private final StudentRepo studentRepo;
    private final EnrolmentRepo enrolmentRepo;

    @Autowired
    public StaffServiceImpl(StudentRepo studentRepo, EnrolmentRepo enrolmentRepo) {
        this.studentRepo = studentRepo;
        this.enrolmentRepo = enrolmentRepo;
    }

    @Override
    public List<Enrolment> viewPastPerformance(Student student) {
        return studentRepo.findEnrolments(student);
    }

}
