package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.repository.CourseOfferingRepo;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : OfferingServiceImpl
 */
@Service
public class CourseOfferingService {
    private final CourseOfferingRepo offeringRepo;
    private final ScheduleService scheduleService;


    @Autowired
    public CourseOfferingService(CourseOfferingRepo offeringRepo, ScheduleService scheduleService) {
        this.offeringRepo = offeringRepo;
        this.scheduleService = scheduleService;
    }

    public CourseOffering findOne(CourseOffering offering) {
        return offeringRepo.getOne(offering.getOfferingId());
    }

    public List<CourseOffering> findByCourse(Course course) {
        return offeringRepo.findAllByCourse(course);
    }

    public List<CourseOffering> findOfferingsInCurrentSemester() {
        Schedule current = scheduleService.findCurrentSchedule();
        return offeringRepo.findCourseOfferingsByYearAndSemester(current.getYear(), current.getSemester());
    }

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

    public CourseOffering save(CourseOffering offering) {
        List<CourseOffering> offerings = offeringRepo.findCourseOfferingsByCourseAndYearAndSemester(offering.getCourse(), offering.getYear(), offering.getSemester());
        if (offerings != null && !offerings.isEmpty()) {
            offering = offerings.get(0);
        }
        return offeringRepo.save(offering);
    }

    public void delete(CourseOffering offering) {
        offeringRepo.delete(offering);
    }

    public boolean deleteIfExist(CourseOffering offering) {
        if (offeringRepo.existsById(offering.getOfferingId())) {
            offeringRepo.delete(offering);
            return true;
        }
        return false;
    }

    public List<CourseOffering> findAll() {
        return offeringRepo.findAll();
    }
}
