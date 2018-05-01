package team.high5;

public class CourseOffering {
    private int OfferingId;
    private Course course;
    private Lecturer lecturer;
    private Schedule schedule;
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
            throw new IllegalArgumentException("capacity should greater than 0");
        }
        this.capacity = capacity;
    }
}
