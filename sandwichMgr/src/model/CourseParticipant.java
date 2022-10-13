package model;

public class CourseParticipant extends Person {

    private Course course;

    public CourseParticipant(String firstName, Course course) {
        super(firstName);
        this.course = course;
        this.setSandwichPayedByAbis(true);
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
