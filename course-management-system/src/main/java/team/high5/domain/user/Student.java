package team.high5.domain.user;

import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends User {

    @Column(name = "maxLoad")
    private int maxLoad = 1;
    @Column(name = "maxElectives")
    private int maxElectives = 1;
    @OneToMany(mappedBy = "enrolId", cascade = CascadeType.ALL)
    private List<Enrolment> performance;

    public int getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public int getMaxElectives() {
        return maxElectives;
    }

    public void setMaxElectives(int maxElectives) {
        this.maxElectives = maxElectives;
    }

    public List<Enrolment> getPerformance() {
        return performance;
    }

    public void setPerformance(List<Enrolment> performance) {
        this.performance = performance;
    }

    public List<Enrolment> viewPastPerformance() {
        List<Enrolment> pasts = new ArrayList<>();
        for (Enrolment enrolment : performance) {
            if (!enrolment.getOffering().getSchedule().equals(Schedule.currentSchedule())) {
                pasts.add(enrolment);
            }
        }
        return pasts;
    }
}
