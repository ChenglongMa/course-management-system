package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Schedule;
import team.high5.repository.ScheduleRepo;
import team.high5.service.ScheduleService;

import java.util.Calendar;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:08
 * @Description :
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepo scheduleRepo;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public Schedule findCurrentSchedule() {
        Schedule cSchedule = scheduleRepo.findScheduleBySchId(1);
        if (cSchedule == null) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            cSchedule = new Schedule(year, 1);
            cSchedule.setSchId(1);
            cSchedule.setWeek(1);
            cSchedule = scheduleRepo.save(cSchedule);
        }
        Schedule.setCurrentSchedule(cSchedule);
        return Schedule.currentSchedule();
    }

    @Override
    public void saveCurrentSchedule() {
        scheduleRepo.save(Schedule.currentSchedule());
    }

    @Override
    public void advance() {
        findCurrentSchedule();
        Schedule.advance();
        saveCurrentSchedule();
    }
}
