package team.high5.domain.user;

import team.high5.domain.entities.CourseOffering;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {

}
