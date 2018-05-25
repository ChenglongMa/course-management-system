package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.user.Student;
import team.high5.repository.EnrolmentRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 14-05-2018
 * @Time : 9:25
 * @Description :
 */
@Service
public class EnrolmentService {

    private final EnrolmentRepo enrolmentRepo;

    @Autowired
    public EnrolmentService(EnrolmentRepo enrolmentRepo) {
        this.enrolmentRepo = enrolmentRepo;
    }

    public List<Enrolment> findAllByResult(String res) {
        return enrolmentRepo.findAllByResult(res);
    }

    public boolean existRNF() {
        List<Enrolment> list = new ArrayList<>();// findAllByResult("");
        for (Enrolment e : enrolmentRepo.findAll()) {
            if (e.getResult() == null || e.getResult().equals("")) {
                list.add(e);
            }
        }
        return list != null && !list.isEmpty();
    }

    public Enrolment enrolCourse(Student student, CourseOffering offering) {
        Enrolment enrolment = new Enrolment(student, offering);
        return save(enrolment);
    }

    public Enrolment save(Enrolment enrolment) {
        Student stu = enrolment.getStudent();
        CourseOffering offering = enrolment.getOffering();
        Enrolment enrol = enrolmentRepo.findEnrolmentByStudentAndOffering(stu, offering);
        if (enrol != null) {
            enrolment.setEnrolId(enrol.getEnrolId());
        }
        return enrolmentRepo.save(enrolment);
    }

    public void saveAll(List<Enrolment> enrolments) {
        enrolmentRepo.saveAll(enrolments);
    }

    public List<Enrolment> getCurrEnrolments(CourseOffering offering) {
        return enrolmentRepo.findAllByOffering(offering);
    }

    public List<Enrolment> findAllByStudent(Student student) {
        return enrolmentRepo.findAllByStudent(student);
    }

    public List<CourseOffering> findOffering(Student student) {
        List<CourseOffering> offerings = new ArrayList<>();
        for (Enrolment enrolment : enrolmentRepo.findAllByStudent(student)) {
            offerings.add(enrolment.getOffering());
        }
        return offerings;
    }
}
