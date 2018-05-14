package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.entities.CourseOffering;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:49
 * @Description : CourseOfferingRepo
 */
public interface CourseOfferingRepo extends JpaRepository<CourseOffering, Integer> {
    List<CourseOffering> findCourseOfferingsByYearAndSemester(int year, int semester);
}
