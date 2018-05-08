package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.user.Lecturer;
import team.high5.repository.AdminRepo;
import team.high5.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public CourseOffering addCourseOffering(CourseOffering offering) {
        if (offering == null) {
            throw new NullPointerException("Unavailable CourseOffering");
        }
        return offering;
    }

    public CourseOffering assignLecturer(CourseOffering offering, Lecturer lecturer) {
        offering.setLecturer(lecturer);
        // TODO: update database
//        throw new UnsupportedOperationException();
        return offering;
    }
}
