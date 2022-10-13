package model;

public class StaffMember extends Person {

    private Course course;

    public StaffMember(String firstName) {
        super(firstName);
        this.setSandwichPayedByAbis(false);
    }

    public Course getCourse() {
        return course;
    }

    public void teachCourse(Course course) {
        this.course = course;
    }
}
