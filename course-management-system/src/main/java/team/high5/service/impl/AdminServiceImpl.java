package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Admin;
import team.high5.domain.user.Lecturer;
import team.high5.domain.user.Student;
import team.high5.repository.AdminRepo;
import team.high5.repository.CourseOfferingRepo;
import team.high5.repository.ScheduleRepo;
import team.high5.service.AdminService;
import team.high5.service.StaffService;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : AdminServiceImpl
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;
    private final CourseOfferingRepo offeringRepo;
    private final ScheduleRepo scheduleRepo;
    private final StaffService staffService;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo,
                            CourseOfferingRepo offeringRepo,
                            ScheduleRepo scheduleRepo,
                            @Qualifier("staffServiceImpl") StaffService staffService) {
        this.adminRepo = adminRepo;
        this.offeringRepo = offeringRepo;
        this.scheduleRepo = scheduleRepo;
        this.staffService = staffService;
    }

    @Override
    public List<Admin> get() {
        return adminRepo.findAll();
    }

    @Override
    public Admin insert(Admin admin) {
        return adminRepo.save(admin);
    }

    @Override
    public CourseOffering addCourseOffering(CourseOffering offering) {
        return offeringRepo.save(offering);
    }

    @Override
    public CourseOffering assignLecturer(CourseOffering offering, Lecturer lecturer) {
        offering.setLecturer(lecturer);
        return offeringRepo.save(offering);
    }

    @Override
    public void advanceSystem() {
        //TODO: To be checked.
        Schedule.setCurrentSchedule(scheduleRepo.findScheduleBySchId(1));
        scheduleRepo.save(Schedule.advance());
    }

    @Override
    public List<Enrolment> viewPastPerformance(Student student) {
        return staffService.viewPastPerformance(student);
    }
}
