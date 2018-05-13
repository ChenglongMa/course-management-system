package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.entities.Enrolment;
import team.high5.repository.EnrolmentRepo;
import team.high5.service.EnrolmentService;

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
public class EnrolmentServiceImpl implements EnrolmentService {

    private final EnrolmentRepo enrolmentRepo;

    @Autowired
    public EnrolmentServiceImpl(EnrolmentRepo enrolmentRepo) {
        this.enrolmentRepo = enrolmentRepo;
    }

    @Override
    public boolean existRNF() {
        List<Enrolment> list = enrolmentRepo.findAllByResult("RNF");
        return list != null && !list.isEmpty();
    }
}
