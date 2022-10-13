package model;

import exception.SessionNotFoundException;
import org.apache.logging.log4j.LogManager;

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

    @Override
    public Sandwich orderSandwich(Shop shop) {
        Sandwich sandwich = super.orderSandwich(shop);
        try {
            this.getCourse().getSessionByDate(LocalDate.now()).getDailyOrder().addSandwich(sandwich);
        } catch(SessionNotFoundException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }

        return sandwich;
    }

}
