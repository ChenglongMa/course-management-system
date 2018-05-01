package team.high5;

import java.util.List;

public class Student extends User {
    private int maxLoad = 1;
    private int maxElectives = 1;
//    private boolean isWaived = false;
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
}
