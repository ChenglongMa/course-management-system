package team.high5.service;

import team.high5.domain.user.User;

import java.util.List;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 15-05-2018
 * @Time : 10:06
 * @Description :
 */
public interface UserService<T extends User> {

    T findOne(T user);

    List<T> findAll();

    T insert(T user);

    T update(T user);

    /**
     * insert or update the entity.
     * @param user
     * @return
     */
    T save(T user);
}
