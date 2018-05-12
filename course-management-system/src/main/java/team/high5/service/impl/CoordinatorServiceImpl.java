package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Course;
import team.high5.domain.user.Student;
import team.high5.service.CoordinatorService;
import team.high5.service.CourseService;
import team.high5.service.StudentService;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:02
 * @Description :
 */
@Service
public class CoordinatorServiceImpl implements CoordinatorService {

    private final CourseService courseService;
    private final StudentService studentService;


    @Autowired
    public CoordinatorServiceImpl(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public Course addCourse(Course course) {
        return courseService.addCourse(course);
    }

    @Override
    public void grantPermission(Student student, int maxLoad) {
        studentService.setMaxLoad(student,maxLoad);
    }

    @Override
    public void grantExemption(Student student, Course course) {
        // TODO: 2018/5/10 0010 To be finished.
    }
}
