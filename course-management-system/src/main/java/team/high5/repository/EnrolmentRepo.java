package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Student;

import java.util.List;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 16:03
 * @Description :
 */
public interface EnrolmentRepo extends JpaRepository<Enrolment, Integer> {

    List<Enrolment> findAllByResult(String result);

    Enrolment findEnrolmentByStudentAndOffering(Student student, CourseOffering offering);

    List<Enrolment> findAllByOffering(CourseOffering offering);

    List<Enrolment> findAllByStudent(Student student);

    List<Enrolment> findAllByOfferingAndStudent(CourseOffering offering, Student student);
}
