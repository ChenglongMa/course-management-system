package team.high5.service;

import team.high5.domain.user.Admin;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : AdminService
 */
public interface AdminService {

    List<Admin> get();

    Admin insert(Admin admin);
}
