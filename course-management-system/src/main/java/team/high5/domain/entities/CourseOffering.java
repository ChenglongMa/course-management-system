package team.high5.domain.entities;

import team.high5.domain.user.Lecturer;

import javax.persistence.*;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:46
 * @Description :
 */
@Entity
@Table(name = "courseoffering")
public class CourseOffering {
    @Column(name = "OfferingId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OfferingId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "code")
    private Course course;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "userId")
    private Lecturer lecturer;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "schId")
    private Schedule schedule;
    @Column(name = "capacity")
    private int capacity;

    public int getOfferingId() {
        return OfferingId;
    }

    public void setOfferingId(int offeringId) {
        OfferingId = offeringId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course == null) {
            throw new NullPointerException();
        }
        this.course = course;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        if (lecturer == null) {
            throw new NullPointerException();
        }
        this.lecturer = lecturer;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        if (schedule == null) {
            throw new NullPointerException();
        }
        this.schedule = schedule;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity should greater than 0");
        }
        this.capacity = capacity;
    }
}
