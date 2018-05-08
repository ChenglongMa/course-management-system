package team.high5.domain.entities;

import team.high5.domain.user.Student;

public class Enrolment {
    private int enrolId;
    private Student student;
    private CourseOffering offering;
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
