package sandwich.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String password;
    private Course course;

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

    public Course getCourse() {
        return this.course;
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
