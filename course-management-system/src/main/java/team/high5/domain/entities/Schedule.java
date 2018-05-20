package team.high5.domain.entities;

import javax.persistence.*;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:48
 * @Description : Schedule
 */
@Entity
@Table(name = "schedule")
public class Schedule implements Comparable<Schedule> {
    @Transient
    public static final int SEMESTER_COUNT = 2;
    @Transient
    public static final int WEEK_COUNT = 12;
    @Transient
    private static Schedule currentSchedule = null;
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

    public Schedule() {
    }

    public Schedule(int year, int semester) {
        this.year = year;
        this.semester = semester;
    }

    /**
     * Returns the current schedule
     *
     * @return
     */
    public static Schedule currentSchedule() {
        if (currentSchedule == null) {
            throw new NullPointerException("Need to be updated from database.");
        }
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

    public static void setCurrentSchedule(Schedule currentSchedule) {
        Schedule.currentSchedule = currentSchedule;
    }

    /**
     * Advance the system to next week or next semester.
     *
     * @return the current schedule
     */
    public static Schedule advance() {
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
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Schedule other = (Schedule) obj;
        return this.getYear() == other.getYear() && this.getSemester() == other.getSemester();
    }

    @Override
    public int compareTo(Schedule o) {
        if (o == null) {
            return 1;
        }
        if (this.year == o.year) {
            return this.semester - o.semester;
        } else return this.year - o.year;
    }
}
