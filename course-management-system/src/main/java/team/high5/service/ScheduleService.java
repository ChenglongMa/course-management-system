package team.high5.service;

import team.high5.domain.entities.Schedule;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:07
 * @Description :
 */

public interface ScheduleService {
    Schedule findCurrentSchedule();

    void saveCurrentSchedule();

    void advance();
}
