package sandwich.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name="userGen", sequenceName = "user_uid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGen")
    @Column(name="uid")
    private int userId;
    @Column(name="fname")
    private String firstName;
    @Column(name="lname")
    private String lastName;
    @Column(name="mail")
    private String email;
    @Column(name="pwd")
    private String password;
    @ManyToOne(cascade = {MERGE, PERSIST})
    private Course course;

    public User() {}

    public User(String firstName, String lastName, String email, String password, Course course) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.course = course;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User user = (User)o;
            return user.userId == this.userId && user.firstName.equals(this.firstName);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash *= 31 + this.userId;
        hash *= 31 + this.firstName.hashCode();
        return hash;
    }
}
