package sandwich.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import sandwich.model.Course;

@JsonRootName(value = "course")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO {

    private String name;

    public CourseDTO() {}

    public CourseDTO(String name) {
        this.name = name;
    }

    public CourseDTO(Course course) {
        this.name = course.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

