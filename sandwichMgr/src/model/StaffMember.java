package model;

public class StaffMember extends Person {

    private Course course;

    public StaffMember(String firstName) {
        super(firstName);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
