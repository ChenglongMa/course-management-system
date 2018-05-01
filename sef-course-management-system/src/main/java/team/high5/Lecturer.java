package team.high5;

import java.util.List;

public class Lecturer extends Staff {
    public void uploadResult(Student student, String result) throws NullPointerException {
        List<Enrolment> enrolments = student.getPerformance();
        Enrolment enrolment = null;
        for (int i = 0; i < enrolments.size(); i++) {

            CourseOffering offering = enrolments.get(i).getOffering();
            if (offering.getLecturer().equals(this) && offering.getSchedule().equals(Schedule.currentSchedule())) {
                enrolment = enrolments.get(i);
                break;
            }
        }
        if (enrolment == null) {
            throw new NullPointerException("There is no such student in the lecturer's course");
        }
        enrolment.setResult(result);
    }

}
