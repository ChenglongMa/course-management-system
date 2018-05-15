package team.high5.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Student;

import java.util.List;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 16:01
 * @Description :
 */
public interface StudentRepo extends UserRepo<Student> {
    @Query("select e from Enrolment e where e.student=:stu")
    List<Enrolment> findEnrolments(@Param("stu") Student student);
}
