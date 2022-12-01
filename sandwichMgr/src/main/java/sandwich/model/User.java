package sandwich.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "users")
public class User {

    @Id @SequenceGenerator(name = "users_generator", sequenceName = "user_uid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @Column(name = "uid")                   private int userId;
    @Column(name = "fname")                 private String firstName;
    @Column(name = "lname")                 private String lastName;
    @Column(name = "pwd")                   private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "u_cid")             private Course course;


    public User() {}

    public User(String username, String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void follow(Course course) {
        this.course = course;
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
