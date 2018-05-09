package team.high5.domain.entities;

import team.high5.domain.user.Student;

import javax.persistence.*;

@Entity
@Table(name = "enrolment")
public class Enrolment {
    @Id
    @Column(name = "enrolId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrolId;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "userId")
    private Student student;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "OfferingId")
    private CourseOffering offering;
    @Column(name = "result")
    private String result = "RNF";

    public int getEnrolId() {
        return enrolId;
    }

    public void setEnrolId(int enrolId) {
        this.enrolId = enrolId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseOffering getOffering() {
        return offering;
    }

    public void setOffering(CourseOffering offering) {
        this.offering = offering;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
