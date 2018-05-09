package team.high5.service;

import team.high5.domain.entities.Course;
import team.high5.domain.user.Student;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:02
 * @Description :
 */
public interface CoordinatorService {
    Course addCourse(Course course);

    void grantPermission(Student student,int maxLoad);

    void grantExemption(Student student, Course course);
}
