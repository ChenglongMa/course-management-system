package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.entities.Enrolment;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 16:03
 * @Description :
 */
public interface EnrolmentRepo extends JpaRepository<Enrolment, Integer> {

}
