package team.high5.service.impl;

import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Student;
import team.high5.service.LecturerService;

import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:50
 * @Description : LecturerServiceImpl
 */
public class LecturerServiceImpl implements LecturerService {


    @Override
    public boolean uploadResult(Student student, String result) throws NullPointerException {
        List<Enrolment> enrolments = student.getPerformance();
        Enrolment enrolment = null;
        for (Enrolment e : enrolments) {

            CourseOffering offering = e.getOffering();
            if (offering.getLecturer().equals(this) && offering.getSchedule().equals(Schedule.currentSchedule())) {
                enrolment = e;
                break;
            }
        }
        if (enrolment == null) {
            throw new NullPointerException("There is no such student in the lecturer's course");
        }
        enrolment.setResult(result);
        return true;
    }
}
