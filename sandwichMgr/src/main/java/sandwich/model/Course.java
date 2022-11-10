package sandwich.model;

import sandwich.exception.ParticipantNotFoundException;
import sandwich.exception.SessionNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private String name;
    private List<User> courseParticipants = new ArrayList<>();
    private List<Session> sessions;

    public Course(String name) {
        this.name = name;
    }

    public void printParticipants() {
        for (User participant: courseParticipants) {
            System.out.println(participant.getFirstName());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getCourseParticipants() {
        return courseParticipants;
    }

    public User getCourseParticipantByName(String firstName) throws ParticipantNotFoundException {
        return this.courseParticipants.stream().filter(e -> e.getFirstName().equalsIgnoreCase(firstName)).findFirst().orElseThrow(() -> new ParticipantNotFoundException("Particpant " + firstName + " not found"));
    }

    public void addParticipants(List<User> courseParticipants) {
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

    public boolean hasSession(LocalDate date) {
        return this.sessions.stream().anyMatch(elem -> elem.coversDate(date));
    }

    public void addParticipant(User participant) {
        this.courseParticipants.add(participant);
    }
}
