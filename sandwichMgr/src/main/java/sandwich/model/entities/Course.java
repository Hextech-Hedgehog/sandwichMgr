package sandwich.model.entities;

import sandwich.exception.ParticipantNotFoundException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name="course")
public class Course {

    @Id
    @SequenceGenerator(name="courseGen", sequenceName = "course_cid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseGen")
    @Column(name="cid")
    private int courseId;
    @Column(name="cname")
    private String name;
    @OneToMany(targetEntity = User.class, cascade = {MERGE, PERSIST})
    @JoinColumn(name="u_cid")
    private List<User> courseParticipants = new ArrayList<>();
    @OneToMany(targetEntity = Session.class, cascade = {MERGE, PERSIST})
    @JoinColumn(name="se_cid")
    private List<Session> sessions;

    public Course(){}

    public Course(String name) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException("Name is a required parameter for course");
        this.name = name;
    }

    public Course(String name, List<User> courseParticipants, List<Session> sessions) {
        this.name = name;
        this.courseParticipants = courseParticipants;
        this.sessions = sessions;
    }

    public void printParticipants() {
        for (User participant: courseParticipants) {
            System.out.println(participant.getFirstName());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException("Name is a required parameter for course");
        this.name = name;
    }

    public List<User> getCourseParticipants() {
        return courseParticipants;
    }

    public void setCourseParticipants(List<User> courseParticipants) {
        this.courseParticipants = courseParticipants;
    }

    public User getCourseParticipantByName(String firstName) throws ParticipantNotFoundException {
        return this.courseParticipants.stream().filter(e -> e.getFirstName().equalsIgnoreCase(firstName)).findFirst().orElseThrow(() -> new ParticipantNotFoundException("Particpant " + firstName + " not found"));
    }

    public void addParticipants(List<User> courseParticipants) {
        this.courseParticipants.addAll(courseParticipants);
        this.courseParticipants.forEach(participant -> participant.setCourse(this));
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public boolean hasSession(LocalDate date) {
        return this.sessions.stream().anyMatch(elem -> elem.coversDate(date));
    }

    public void addParticipant(User participant) {
        this.courseParticipants.add(participant);
    }

    public int getCourseId() {
        return courseId;
    }
}
