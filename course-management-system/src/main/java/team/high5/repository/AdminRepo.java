package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.user.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
}
