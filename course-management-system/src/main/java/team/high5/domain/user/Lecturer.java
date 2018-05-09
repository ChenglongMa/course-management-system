package team.high5.domain.user;

import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "lecturer")
public class Lecturer extends User {
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
