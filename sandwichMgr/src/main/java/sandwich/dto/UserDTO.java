package sandwich.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import sandwich.model.User;

import javax.validation.constraints.Pattern;

@JsonRootName(value = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {


    @JacksonXmlProperty(localName = "userid")
    private int userId;                                         // TODO Quid userId in UserDTO ?
    private String firstName;
    private String lastName;
    @Pattern(regexp = "\\w{8,}", message = "password needs to be at least 8 characters long")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(value = "course")
    private CourseDTO courseDTO;

    public UserDTO() {}

    public UserDTO(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.courseDTO = new CourseDTO(user.getCourse());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }
}

