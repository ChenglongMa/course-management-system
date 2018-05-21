package team.high5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.user.User;
import team.high5.repository.UserRepo;

import java.util.List;
import java.util.Optional;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 15-05-2018
 * @Time : 9:22
 * @Description :
 */
@Service
public class UserService<T extends User> {
    private final UserRepo<T> userRepo;

    @Autowired
    public UserService(UserRepo<T> userRepo) {
        this.userRepo = userRepo;
    }

    public T findOne(T user) {
        Optional<T> op = userRepo.findById(user.getUserId());
        return op.orElse(null);
    }

    public List<T> findAll() {
        return userRepo.findAll();
    }

    public T insert(T user) {
        if (userRepo.existsById(user.getUserId())) {
            throw new IllegalArgumentException("This user has existed.");
        }
        return userRepo.save(user);
    }

    public T update(T user) {
        if (userRepo.existsById(user.getUserId())) {
            return userRepo.save(user);
        }
        throw new IllegalArgumentException("Cannot find such user.");
    }

    public T save(T user) {
        return userRepo.save(user);
    }
}
