package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Course;
import team.high5.domain.user.Student;
import team.high5.repository.CourseRepo;
import team.high5.repository.StudentRepo;
import team.high5.service.CoordinatorService;

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

    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;


    @Autowired
    public CoordinatorServiceImpl(CourseRepo courseRepo, StudentRepo studentRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public Course addCourse(Course course) {
        if (courseRepo.existsById(course.getCode())) {
            throw new IllegalArgumentException("The course has existed.");
        }
        return courseRepo.save(course);
    }

    @Override
    public void grantPermission(Student student, int maxLoad) {
        student.setMaxLoad(maxLoad);
        studentRepo.save(student);
    }

    @Override
    public void grantExemption(Student student, Course course) {
        // TODO: 2018/5/10 0010 To be finished.
    }
}
