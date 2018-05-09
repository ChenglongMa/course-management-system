package team.high5.service;

import team.high5.domain.entities.CourseOffering;
import team.high5.domain.user.Lecturer;

public interface CourseOfferingService {
    CourseOffering addCourseOffering(CourseOffering offering);

    CourseOffering assignLecturer(Lecturer lecturer);
}
