package team.high5.service;

import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Lecturer;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : CourseOfferingService
 */
public interface CourseOfferingService {
    Lecturer findLecturer(CourseOffering offering);

    Course findCourse(CourseOffering offering);

    Schedule findSchedule(CourseOffering offering);

    List<CourseOffering> findOfferingsInCurrentSemester();
}
