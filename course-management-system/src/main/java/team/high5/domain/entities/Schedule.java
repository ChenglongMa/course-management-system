package team.high5.domain.entities;

import com.sun.org.apache.xpath.internal.operations.Equals;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule extends Equals {
    @Id
    @Column(name = "schId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schId;
    @Column(name = "year")
    private int year;
    @Column(name = "semester")
    private int semester;
    @Column(name = "week")
    private int week;
    @Transient
    private static final int SEMESTER_COUNT = 2;
    @Transient
    private static final int WEEK_COUNT = 12;
    @Transient
    private static Schedule currentSchedule = new Schedule();

    /**
     * Returns the current schedule
     *
     * @return
     */
    public static Schedule currentSchedule() {
        return currentSchedule;
    }

    public int getSchId() {
        return schId;
    }

    public void setSchId(int schId) {
        this.schId = schId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Schedule advance() {
        int week = currentSchedule.getWeek();
        if (week <= 0)
            throw new IllegalArgumentException(String.format("Wrong Week: %s", week));
        if (week < WEEK_COUNT) {
            currentSchedule.setWeek(week + 1);
        } else {
            int semester = currentSchedule.getSemester();
            if (semester <= 0)
                throw new IllegalArgumentException(String.format("Wrong Semester: %s", semester));
            if (semester >= SEMESTER_COUNT) {
                int year = currentSchedule.getYear();
                currentSchedule.setYear(year + 1);
                currentSchedule.setSemester(1);
            } else {
                currentSchedule.setSemester(semester + 1);
            }
            currentSchedule.setWeek(1);
        }
        return currentSchedule;
    }

    /**
     * Returns true if the schedule has same year and semester.
     *
     * @param obj the schedule to be compared.
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Schedule other = (Schedule) obj;
        return this.getYear() == other.getYear() && this.getSemester() == other.getSemester();
    }
}
