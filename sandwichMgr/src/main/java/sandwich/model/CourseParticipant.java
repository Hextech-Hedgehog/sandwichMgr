package sandwich.model;

import sandwich.exception.SandwichNotFoundException;
import sandwich.exception.SessionNotFoundException;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.time.LocalDate;

public class CourseParticipant extends Person {

    private Course course;

    public CourseParticipant(String firstName) {
        super(firstName);
    }

    public Course getCourse() {
        return course;
    }

    public void follow(Course course) {
        if (course != null) {
            this.course = course;
        }
    }

}
