package team.high5.domain.user;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coordinator")
public class Coordinator extends User {
    public void grantPermission(Student student) {

    }
}
