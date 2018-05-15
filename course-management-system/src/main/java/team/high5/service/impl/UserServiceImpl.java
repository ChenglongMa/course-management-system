package team.high5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.high5.domain.user.User;
import team.high5.repository.UserRepo;
import team.high5.service.UserService;

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
public class UserServiceImpl<T extends User> implements UserService<T> {
    private final UserRepo<T> userRepo;

    @Autowired
    public UserServiceImpl(UserRepo<T> userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public T findOne(T user) {
        Optional<T> op = userRepo.findById(user.getUserId());
        return op.orElse(null);
    }

    @Override
    public T insert(T user) {
        if (userRepo.existsById(user.getUserId())) {
            throw new IllegalArgumentException("This user has existed.");
        }
        return userRepo.save(user);
    }

    @Override
    public T update(T user) {
        if (userRepo.existsById(user.getUserId())) {
            return userRepo.save(user);
        }
        throw new IllegalArgumentException("Cannot find such user.");
    }

    @Override
    public T save(T user) {
        return userRepo.save(user);
    }
}
