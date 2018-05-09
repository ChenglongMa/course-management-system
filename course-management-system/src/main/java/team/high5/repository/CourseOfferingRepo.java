package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.entities.CourseOffering;

public interface CourseOfferingRepo extends JpaRepository<CourseOffering,Integer> {
}
