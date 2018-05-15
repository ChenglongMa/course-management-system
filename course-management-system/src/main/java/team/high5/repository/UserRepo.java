package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.user.User;

import java.util.List;

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
