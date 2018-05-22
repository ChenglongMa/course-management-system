package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Enrolment;
import team.high5.repository.EnrolmentRepo;

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
        List<Enrolment> list = findAllByResult("");
        return list != null && !list.isEmpty();
    }

    public Enrolment save(Enrolment enrolment) {
        return enrolmentRepo.save(enrolment);
    }

    public void saveAll(List<Enrolment> enrolments) {
        enrolmentRepo.saveAll(enrolments);
    }
}
