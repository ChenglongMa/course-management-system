package team.high5.service.impl;

import team.high5.domain.user.Student;
import team.high5.service.LecturerService;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : LecturerServiceImpl
 */
public class LecturerServiceImpl extends StaffServiceImpl implements LecturerService {
    @Override
    public boolean uploadResult(Student student, String result) {
        return false;
    }
}
