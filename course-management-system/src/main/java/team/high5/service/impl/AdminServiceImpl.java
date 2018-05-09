package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.user.Admin;
import team.high5.repository.AdminRepo;
import team.high5.service.AdminService;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : AdminServiceImpl
 */
@Service
public class AdminServiceImpl extends StaffServiceImpl implements AdminService {
    private final AdminRepo adminRepo;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }


    @Override
    public List<Admin> get() {
        return adminRepo.findAll();
    }

    @Override
    public Admin insert(Admin admin) {
        return adminRepo.save(admin);
    }
}
