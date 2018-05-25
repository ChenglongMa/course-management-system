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
@Table(name = "courseOffering")
public class CourseOffering {
    @Column(name = "capacity")
    private int capacity = 100;
    @Column(name = "OfferingId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OfferingId;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "code")
    private Course course;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "userId")
    private Lecturer lecturer;
    @Column(name = "year")
    private int year;
    @Column(name = "semester")
    private int semester;

    public CourseOffering() {
        course = new Course();
    }

    public CourseOffering(Course course) {
        this(course, Schedule.currentSchedule());
    }

    public CourseOffering(Course course, Schedule schedule) {
        this.course = course;
        year = schedule.getYear();
        semester = schedule.getSemester();
    }

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
        return new Schedule(year, semester);
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

    @Override
    public String toString() {
        String code = course.getCode();
        String name = course.getName();
        String lec = lecturer == null ? "TBA" : lecturer.getName();
        return String.format("Code: %s Name: %s Capacity: %d Year: %d Semester: %d Lecturer: %s",
                code, name, capacity, year, semester, lec);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        CourseOffering other = (CourseOffering) obj;
        return this.course.equals(other.course) && this.getSchedule().equals(other.getSchedule());
    }
}
