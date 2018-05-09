package team.high5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.high5.domain.entities.Schedule;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:18
 * @Description :
 */
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
    Schedule findScheduleBySchId(int id);
}
