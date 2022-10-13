package model;

import exception.ParticipantNotFoundException;
import exception.SessionNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Course {

    private String name;
    private List<CourseParticipant> courseParticipants = new ArrayList<>();
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

    public CourseParticipant getCourseParticipantByName(String firstName) throws ParticipantNotFoundException {
        return this.courseParticipants.stream().filter(e -> e.getFirstName().equalsIgnoreCase(firstName)).findFirst().orElseThrow(() -> new ParticipantNotFoundException("Particpant " + firstName + " not found"));
    }

    public void addParticipants(List<CourseParticipant> courseParticipants) {
        this.courseParticipants.addAll(courseParticipants);
        this.courseParticipants.forEach(participant -> participant.follow(this));
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Session getSessionByDate(LocalDate date) throws SessionNotFoundException {
        return this.sessions.stream().filter(elem -> elem.coversDate(date)).findFirst().orElseThrow(() -> new SessionNotFoundException("No session found at this date: " + date));
    }

    public void addParticipant(CourseParticipant participant) {
        this.courseParticipants.add(participant);
    }
}
