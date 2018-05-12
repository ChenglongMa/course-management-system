package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Student;
import team.high5.repository.StudentRepo;
import team.high5.service.CourseOfferingService;
import team.high5.service.ScheduleService;
import team.high5.service.StudentService;

import java.util.List;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:04
 * @Description :
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final ScheduleService scheduleService;
    private final CourseOfferingService offeringService;


    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, ScheduleService scheduleService, CourseOfferingService offeringService) {
        this.studentRepo = studentRepo;
        this.scheduleService = scheduleService;
        this.offeringService = offeringService;
    }

    @Override
    public Student findOne(Student student) {
        return studentRepo.getOne(student.getUserId());
    }

    @Override
    public List<Enrolment> findEnrolments(Student student) {
        Student stu = findOne(student);
        if (stu != null) {
            return stu.getPerformance();
        }
        return null;
    }

    @Override
    public int findMaxLoad(Student student) {
        Student stu = findOne(student);
        if (stu != null) {
            return stu.getMaxLoad();
        }
        return -1;
    }

    @Override
    public int findMaxElectives(Student student) {
        Student stu = findOne(student);
        if (stu != null) {
            return stu.getMaxElectives();
        }
        return -1;
    }

    @Override
    public boolean enrol(Student student, CourseOffering offering) {
        boolean res = offering.getSchedule().equals(scheduleService.findCurrentSchedule());
        if (!res) {
            throw new IllegalArgumentException("The offering is not for this semester.");
        }
        // TODO: 2018/5/9 0009 To be finished.
        return false;
    }

    @Override
    public void setMaxElectives(Student student, int maxElectives) {
        student.setMaxElectives(maxElectives);
        studentRepo.save(student);
    }

    @Override
    public void setMaxLoad(Student student, int maxLoad) {
        student.setMaxLoad(maxLoad);
        studentRepo.save(student);
    }

    @Override
    public List<CourseOffering> viewCourseOffering(Student student) {
        List<CourseOffering> offerings = offeringService.findOfferingsInCurrentSemester();
        // TODO: 2018/5/9 0009 To be finished. filter those course offerings the student can enrol.
        return null;
    }

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

}
