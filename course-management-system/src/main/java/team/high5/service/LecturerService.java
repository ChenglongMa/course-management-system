package team.high5.service;

import team.high5.domain.user.Lecturer;
import team.high5.domain.user.Student;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : LecturerService
 */
public interface LecturerService extends StaffService<Lecturer> {
    boolean uploadResult(Lecturer lecturer, Student student, String result);
}
