package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.user.Lecturer;
import team.high5.repository.CourseOfferingRepo;
import team.high5.service.CourseOfferingService;

public class CourseOfferingServiceImpl implements CourseOfferingService {
    private final CourseOfferingRepo offeringRepo;

    @Autowired
    public CourseOfferingServiceImpl(CourseOfferingRepo offeringRepo) {
        this.offeringRepo = offeringRepo;
    }

    @Override
    public CourseOffering addCourseOffering(CourseOffering offering) {
        if (offering == null) {
            throw new NullPointerException("Unavailable CourseOffering");
        }
        return offering;
    }

    @Override
    public CourseOffering assignLecturer(Lecturer lecturer) {
//        offering.setLecturer(lecturer);
//        // TODO: update database
////        throw new UnsupportedOperationException();
//        return offering;
        return null;
    }
}
