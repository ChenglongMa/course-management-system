package team.high5.domain.user;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:48
 * @Description : Lecturer
 */
@Entity
@Table(name = "lecturer")
public class Lecturer extends User {
    public Lecturer(String userId, String pwd) {
        super(userId, pwd);
    }

    public Lecturer() {

    }
}
