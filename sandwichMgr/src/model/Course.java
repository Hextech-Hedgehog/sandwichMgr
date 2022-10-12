package model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Session> getSessionByDate(LocalDate date) {
        List<Session> sessions = this.sessions.stream().filter(elem -> (elem.getStartDate().isBefore(date) || elem.getStartDate().isEqual(date)) &&
                (elem.getEndDate().isAfter(date) || elem.getEndDate().isEqual(date))).collect(Collectors.toList());
        return sessions;
    }
}
