package team.high5.service;

import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : CourseOfferingService
 */
public interface CourseOfferingService {

    CourseOffering findOne(CourseOffering offering);

    List<CourseOffering> findByCourse(Course course);

    List<CourseOffering> findOfferingsInCurrentSemester();

    CourseOffering addCourseOffering(CourseOffering offering);

    CourseOffering save(CourseOffering offering);

    void delete(CourseOffering offering);

    boolean deleteIfExist(CourseOffering offering);

    List<CourseOffering> findAll();
}
