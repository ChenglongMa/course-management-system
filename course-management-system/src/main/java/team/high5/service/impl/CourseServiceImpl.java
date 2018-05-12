package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Course;
import team.high5.repository.CourseRepo;
import team.high5.service.CourseService;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 12-05-2018
 * @Time : 10:23
 * @Description :
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;

    @Autowired
    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public Course addCourse(Course course) {
        if (courseRepo.existsById(course.getCode())) {
            throw new IllegalArgumentException("The course has existed.");
        }
        return courseRepo.save(course);
    }
}
