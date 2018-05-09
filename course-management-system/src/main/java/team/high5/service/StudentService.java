package team.high5.service;

import team.high5.domain.entities.CourseOffering;
import team.high5.domain.user.Student;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:04
 * @Description :
 */
public interface StudentService {
    Student findOne(String id);

    boolean enrol(Student student, CourseOffering offering);
}
