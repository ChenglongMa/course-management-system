package team.high5;

import java.util.ArrayList;
import java.util.List;

public abstract class Staff extends User{
    public List<Enrolment> viewPastPerformance(Student student) {
        List<Enrolment> enrolments = student.getPerformance();
        List<Enrolment> pasts = new ArrayList<>();
        for (int i = 0; i < enrolments.size(); i++) {
            if (!enrolments.get(i).getOffering().getSchedule().equals(Schedule.currentSchedule())) {
                pasts.add(enrolments.get(i));
            }
        }

        return pasts;
    }

}
