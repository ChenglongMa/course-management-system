package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.user.Lecturer;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 16:00
 * @Description :
 */
public interface LecturerRepo extends UserRepo<Lecturer> {

}
