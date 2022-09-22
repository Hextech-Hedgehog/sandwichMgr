package model;

import java.util.List;

public class Course {

    private String name;
    private List<CourseParticipant> courseParticipants;
    private List<Session> sessions;

    public Course(String name) {
        this.name = name;
    }

    public void printParticipants() {
        for (CourseParticipant participant: courseParticipants) {
            System.out.println(participant.getFirstName());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseParticipant> getCourseParticipants() {
        return courseParticipants;
    }

    public void setCourseParticipants(List<CourseParticipant> courseParticipants) {
        this.courseParticipants = courseParticipants;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
