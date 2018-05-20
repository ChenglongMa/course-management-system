package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Lecturer;
import team.high5.repository.CourseOfferingRepo;
import team.high5.service.CourseOfferingService;
import team.high5.service.ScheduleService;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : OfferingServiceImpl
 */
@Service
public class OfferingServiceImpl implements CourseOfferingService {
    private final CourseOfferingRepo offeringRepo;
    private final ScheduleService scheduleService;


    @Autowired
    public OfferingServiceImpl(CourseOfferingRepo offeringRepo, ScheduleService scheduleService) {
        this.offeringRepo = offeringRepo;
        this.scheduleService = scheduleService;
    }

    @Override
    public Lecturer findLecturer(CourseOffering offering) {
        CourseOffering off = offeringRepo.getOne(offering.getOfferingId());
        return off.getLecturer();
    }

    @Override
    public Course findCourse(CourseOffering offering) {
        CourseOffering off = offeringRepo.getOne(offering.getOfferingId());
        return off.getCourse();
    }

    @Override
    public Schedule findSchedule(CourseOffering offering) {
        CourseOffering off = offeringRepo.getOne(offering.getOfferingId());
        return off.getSchedule();
    }

    @Override
    public List<CourseOffering> findOfferingsInCurrentSemester() {
        Schedule current = scheduleService.findCurrentSchedule();
        return offeringRepo.findCourseOfferingsByYearAndSemester(current.getYear(), current.getSemester());
    }

    @Override
    public CourseOffering addCourseOffering(CourseOffering offering) {
        if (offering == null) {
            throw new NullPointerException("CourseOffering is null");
        }
        List<CourseOffering> offeringschk =
                offeringRepo.findCourseOfferingsByCourseAndYearAndSemester(offering.getCourse(), offering.getYear(), offering.getSemester());
        if (offeringRepo.existsById(offering.getOfferingId()) || (offeringschk != null && !offeringschk.isEmpty())) {
            throw new IllegalArgumentException("This offering has existed.");
        }
        return offeringRepo.save(offering);
    }

    @Override
    public CourseOffering save(CourseOffering offering) {
        List<CourseOffering> offerings = offeringRepo.findCourseOfferingsByCourseAndYearAndSemester(offering.getCourse(), offering.getYear(), offering.getSemester());
        if (offerings != null && !offerings.isEmpty()) {
            offering = offerings.get(0);
        }
        return offeringRepo.save(offering);
    }

    @Override
    public void deleteOffering(CourseOffering offering) {
        offeringRepo.delete(offering);
    }

    @Override
    public boolean deleteIfExist(CourseOffering offering) {
        if (offeringRepo.existsById(offering.getOfferingId())) {
            offeringRepo.delete(offering);
            return true;
        }
        return false;
    }

    @Override
    public List<CourseOffering> findAll() {
        return offeringRepo.findAll();
    }
}
