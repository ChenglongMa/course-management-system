package team.high5.service;

import team.high5.domain.entities.Course;

import java.util.List;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 12-05-2018
 * @Time : 10:22
 * @Description :
 */
public interface CourseService {
    Course addCourse(Course course);

    Course findCourseByCode(String code);

    List<Course> findAll();

    Course saveCourse(Course course);

    void deleteCourse(Course course);

    boolean deleteIfExist(Course course);
}
