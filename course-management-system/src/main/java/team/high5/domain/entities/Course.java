package team.high5.domain.entities;

import javax.persistence.*;
import java.util.List;

/**
 * @Author : Charles Ma
 * @Date : 2018/5/9 0009
 * @Time : 14:45
 * @Description :
 */
@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "mainTopic")
    private String mainTopic;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Course> prerequisites;

    @OneToMany(mappedBy = "OfferingId")
    private List<CourseOffering> courseOfferings;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainTopic() {
        return mainTopic;
    }

    public void setMainTopic(String mainTopic) {
        this.mainTopic = mainTopic;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }

    public void setCourseOfferings(List<CourseOffering> courseOfferings) {
        this.courseOfferings = courseOfferings;
    }
}
