package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.user.Admin;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:49
 * @Description : AdminRepo
 */
public interface AdminRepo extends JpaRepository<Admin, String> {
}
