package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Schedule;
import team.high5.repository.ScheduleRepo;

import java.util.Calendar;
import java.util.Optional;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:08
 * @Description :
 */
@Service
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;

    @Autowired
    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public Schedule findCurrentSchedule() {
        Optional<Schedule> op = scheduleRepo.findById(1);
        Schedule cSchedule;
        if (!op.isPresent()) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            cSchedule = new Schedule(year, 1);
            cSchedule.setSchId(1);
            cSchedule.setWeek(1);
            cSchedule = scheduleRepo.save(cSchedule);
        } else cSchedule = op.get();
        Schedule.setCurrentSchedule(cSchedule);
        return Schedule.currentSchedule();
    }

    public void saveCurrentSchedule() {
        scheduleRepo.save(Schedule.currentSchedule());
    }

    public void advance() {
        findCurrentSchedule();
        Schedule.advance();
        saveCurrentSchedule();
    }
}
