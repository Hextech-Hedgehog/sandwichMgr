package model;

public class CourseParticipant extends Person {

    private Course course;

    public CourseParticipant(String firstName) {
        super(firstName);
    }



    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
