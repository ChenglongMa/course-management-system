package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import team.high5.domain.entities.Schedule;
import team.high5.repository.ScheduleRepo;
import team.high5.service.ScheduleService;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:08
 * @Description :
 */
@Deprecated
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepo scheduleRepo;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public Schedule findCurrentSchedule() {
        Schedule cSchedule = scheduleRepo.findScheduleBySchId(1);
        Schedule.setCurrentSchedule(cSchedule);
        return cSchedule;
    }

    @Deprecated
    @Override
    public void saveCurrentSchedule() {
        scheduleRepo.save(Schedule.currentSchedule());
    }
}
