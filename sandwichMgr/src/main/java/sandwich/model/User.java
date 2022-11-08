package sandwich.model;


import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class User extends org.springframework.security.core.userdetails.User {

    private int userId;
    private String firstName;
    private String lastName;
    private Course course;

    public User(String username) {
        this(username, "password", new HashSet<UserRole>() {{
            add(UserRole.USER);
        }});
    }

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.firstName = username;
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

    public Course getCourse() {
        return this.course;
    }

    public void follow(Course course) {
        this.course = course;
    }
}
