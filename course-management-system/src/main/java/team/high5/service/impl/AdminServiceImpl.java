package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.user.Admin;
import team.high5.domain.user.Lecturer;
import team.high5.repository.AdminRepo;
import team.high5.service.AdminService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }



    @Override
    public List<Admin> getAdmin() {
        return adminRepo.findAll();
    }

}
