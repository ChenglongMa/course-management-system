package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Admin;
import team.high5.domain.user.Lecturer;
import team.high5.repository.AdminRepo;
import team.high5.service.*;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : AdminServiceImpl
 */
@Service
public class AdminServiceImpl extends StaffServiceImpl<Admin> implements AdminService {
    private final AdminRepo adminRepo;
    private final CourseOfferingService offeringService;
    private final ScheduleService scheduleService;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo,
                            CourseOfferingService offeringService,
                            ScheduleService scheduleService,
                            StudentService studentService) {
        super(studentService, adminRepo);
        this.adminRepo = adminRepo;
        this.offeringService = offeringService;
        this.scheduleService = scheduleService;
    }

    @Override
    public List<Admin> get() {
        return adminRepo.findAll();
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepo.save(admin);
    }

    @Override
    public CourseOffering addCourseOffering(CourseOffering offering) {
        if (offering == null) {
            throw new NullPointerException("CourseOffering cannot be null");
        }
        if (offering.getSchedule().compareTo(Schedule.currentSchedule()) < 0) {
            throw new IllegalArgumentException("Cannot add the past Course offering.");
        }
        return offeringService.addCourseOffering(offering);
    }

    @Override
    public CourseOffering assignLecturer(CourseOffering offering, Lecturer lecturer) {
        if (offering==null||lecturer==null) {
            throw new NullPointerException("Offering and lecturer cannot be null");
        }
        if (!offering.getSchedule().equals(Schedule.currentSchedule())) {
            throw new IllegalArgumentException("Cannot assign the lecturer to the offering in non-current semester");
        }
        offering.setLecturer(lecturer);
        return offeringService.save(offering);
    }

    @Override
    public void advanceSystem() {
        scheduleService.advance();
    }

}
