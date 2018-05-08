package team.high5.service;

import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.user.Lecturer;

public interface AdminService {
    CourseOffering addCourseOffering(CourseOffering offering);

    CourseOffering assignLecturer(CourseOffering offering, Lecturer lecturer);
}
