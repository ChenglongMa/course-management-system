package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.user.User;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:48
 * @Description :
 */
//@Deprecated
public interface UserRepo<T extends User> extends JpaRepository<T, String> {

}
