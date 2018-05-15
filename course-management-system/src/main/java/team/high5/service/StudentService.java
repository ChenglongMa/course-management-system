package team.high5.service;

import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Student;

import java.util.List;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:04
 * @Description :
 */
public interface StudentService extends UserService<Student>{

    List<Enrolment> findEnrolments(Student student);

    int findMaxLoad(Student student);

    int findMaxElectives(Student student);

    boolean enrol(Student student, CourseOffering offering);

    void setMaxElectives(Student student, int maxElectives);

    void setMaxLoad(Student student, int maxLoad);

    List<CourseOffering> viewCourseOffering(Student student);

}
