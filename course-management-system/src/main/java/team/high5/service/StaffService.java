package team.high5.service;

import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Student;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : StaffService
 */
public interface StaffService {
    List<Enrolment> viewPastPerformance(Student student);
}
