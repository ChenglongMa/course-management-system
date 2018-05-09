package team.high5.service;

import team.high5.domain.user.Student;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 09-05-2018
 * @Time : 15:02
 * @Description :
 */
public interface CoordinatorService {
    void grantPermission(Student student);
}
