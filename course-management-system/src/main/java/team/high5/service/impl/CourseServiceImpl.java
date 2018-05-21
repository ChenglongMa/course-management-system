package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Course;
import team.high5.repository.CourseRepo;
import team.high5.service.CourseService;

import java.util.List;

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
        if (course == null) {
            throw new NullPointerException("Course is null");
        }
        if (courseRepo.existsById(course.getCode())) {
            throw new IllegalArgumentException("The course has existed.");
        }
        return courseRepo.save(course);
    }

    @Override
    public Course findCourseByCode(String code) {
        return courseRepo.findById(code).orElse(null);
    }

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course save(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public void delete(Course course) {
        courseRepo.delete(course);
    }

    @Override
    public boolean deleteIfExist(Course course) {
        if (courseRepo.existsById(course.getCode())) {
            courseRepo.delete(course);
            return true;
        }
        return false;
    }
}
