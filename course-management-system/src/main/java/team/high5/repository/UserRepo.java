package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.user.User;

public interface UserRepo extends JpaRepository<User, String> {

}
