package team.high5.service;

import team.high5.domain.entities.CourseOffering;
import team.high5.domain.user.Admin;
import team.high5.domain.user.Lecturer;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : AdminService
 */
public interface AdminService extends StaffService<Admin> {

    CourseOffering addCourseOffering(CourseOffering offering);

    CourseOffering assignLecturer(CourseOffering offering, Lecturer lecturer);

    void advanceSystem();
}
