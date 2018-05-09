package team.high5.service;

import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.user.Admin;
import team.high5.domain.user.Lecturer;

import java.util.List;

public interface AdminService {

    List<Admin> getAdmin();
}
