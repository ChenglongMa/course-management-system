package team.high5;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Staff {

    public CourseOffering addCourseOffering(CourseOffering offering) {
        if (offering == null) {
            throw new NullPointerException("Unavailable CourseOffering");
        }
        return offering;
    }

    public CourseOffering assignLecturer(CourseOffering offering, Lecturer lecturer) {
        offering.setLecturer(lecturer);
        // TODO: update database
//        throw new UnsupportedOperationException();
        return offering;
    }





}
