package sandwich.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "course")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO {

    private int courseId;
    private String name;
    private List<UserDTO> courseParticipants;
    private List<SessionDTO> sessions;

    public CourseDTO() {}

    public CourseDTO(String name, List<UserDTO> courseParticipants, List<SessionDTO> sessions) {
        this.name = name;
        this.courseParticipants = courseParticipants;
        this.sessions = sessions;
    }

    public CourseDTO(int courseId, String name, List<UserDTO> courseParticipants, List<SessionDTO> sessions) {
        this(name, courseParticipants, sessions);
        this.courseId = courseId;
    }

    public CourseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<UserDTO> getCourseParticipants() {
        return courseParticipants;
    }

    public void setCourseParticipants(List<UserDTO> courseParticipants) {
        this.courseParticipants = courseParticipants;
    }

    public List<SessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionDTO> sessions) {
        this.sessions = sessions;
    }
}

