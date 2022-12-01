package sandwich.model;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id @SequenceGenerator(name = "course_generator", sequenceName = "course_cid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_generator")
    @Column(name = "cid")               private int courseId;
    @Column(name = "cname")             private String name;


    public Course() {}

    public Course(String name) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException("Name is a required parameter for course");
        this.name = name;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException("Name is a required parameter for course");
        this.name = name;
    }


}
