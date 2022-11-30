package sandwich.dto;

import com.fasterxml.jackson.annotation.*;
import javax.validation.constraints.Pattern;

@JsonRootName(value = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private int userId;
    @Pattern(regexp = "\\w{8,}", message = "password needs to be at least 8 characters long")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String firstname;
    private String lastname;
    ///TODO add pattern validation on mail
    private String email;
    @JsonProperty(value = "course")
    private CourseDTO course;

    public UserDTO() {}

    public UserDTO(int userId, String password, String firstname, String lastname, String email, CourseDTO course) {
        this.userId = userId;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.course = course;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO courseDTO) {
        this.course = courseDTO;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

