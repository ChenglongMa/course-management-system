package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Course;
import team.high5.repository.CourseRepo;

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
public class CourseService {
    private final CourseRepo courseRepo;

    @Autowired
    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course addCourse(Course course) {
        if (course == null) {
            throw new NullPointerException("Course is null");
        }
        if (courseRepo.existsById(course.getCode())) {
            throw new IllegalArgumentException("The course has existed.");
        }
        return courseRepo.save(course);
    }

    public Course findCourseByCode(String code) {
        return courseRepo.findById(code).orElse(null);
    }

    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    public Course save(Course course) {
        return courseRepo.saveAndFlush(course);
    }

    public void delete(Course course) {
        courseRepo.delete(course);
    }

    public boolean deleteIfExist(Course course) {
        if (courseRepo.existsById(course.getCode())) {
            courseRepo.delete(course);
            return true;
        }
        return false;
    }
}
