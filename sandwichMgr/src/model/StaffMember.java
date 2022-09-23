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

    public void setCourse(Course course) {
        this.course = course;
        this.setSandwichPayedByAbis(course != null);
    }
}
