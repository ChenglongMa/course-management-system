package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Admin;
import team.high5.domain.user.Lecturer;
import team.high5.repository.AdminRepo;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : AdminServiceImpl
 */
@Service
public class AdminService extends StaffService<Admin> {
    private final AdminRepo adminRepo;
    private final CourseOfferingService offeringService;
    private final ScheduleService scheduleService;
    private final EnrolmentService enrolmentService;

    @Autowired
    public AdminService(AdminRepo adminRepo,
                        CourseOfferingService offeringService,
                        ScheduleService scheduleService,
                        StudentService studentService, EnrolmentService enrolmentService) {
        super(studentService, adminRepo);
        this.adminRepo = adminRepo;
        this.offeringService = offeringService;
        this.scheduleService = scheduleService;
        this.enrolmentService = enrolmentService;
    }


    public CourseOffering addCourseOffering(CourseOffering offering) {
        if (offering == null) {
            throw new NullPointerException("CourseOffering cannot be null");
        }
        if (offering.getSchedule().compareTo(Schedule.currentSchedule()) < 0) {
            throw new IllegalArgumentException("Cannot add the past Course offering.");
        }
        return offeringService.addCourseOffering(offering);
    }

    public CourseOffering assignLecturer(CourseOffering offering, Lecturer lecturer) {
        if (offering == null || lecturer == null) {
            throw new NullPointerException("Offering and lecturer cannot be null");
        }
        if (!offering.getSchedule().equals(Schedule.currentSchedule())) {
            throw new IllegalArgumentException("Cannot assign the lecturer to the offering in non-current semester");
        }
        offering.setLecturer(lecturer);
        return offeringService.save(offering);
    }

    public void advanceSystem() {
        scheduleService.advance();
    }

    public void markRNF() {
        List<Enrolment> enrolments = enrolmentService.findAllByResult("");
        for (Enrolment enrolment : enrolments) {
            enrolment.setResult("RNF");
        }
        enrolmentService.saveAll(enrolments);
    }

}
