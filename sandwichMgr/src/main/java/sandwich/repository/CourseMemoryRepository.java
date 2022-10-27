package sandwich.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sandwich.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseMemoryRepository implements CourseRepository {

    private List<Course> courses = new ArrayList<>();
    private PersonRepository personRepository;

    public CourseMemoryRepository(@Autowired PersonRepository personRepository) {
        this.personRepository = personRepository;
        Course java = new Course("Java Se programming");
        Course fencing = new Course("Medieval fencing");
        Course baseJump = new Course("Base jumping");
        Person teacher = new StaffMember("Albus");
        Person teacher2 = new StaffMember("Jeff");
        Person teacher3 = new StaffMember("Melene");

        List<Session> sessionsJava = new ArrayList<Session>(){{
            add(new Session(java.getName(), (StaffMember) teacher, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(java.getName(), (StaffMember) teacher, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(java.getName(), (StaffMember) teacher, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(java.getName(), (StaffMember) teacher, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};
        List<Session> sessionsFencing = new ArrayList<Session>(){{
            add(new Session(fencing.getName(), (StaffMember) teacher2, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(fencing.getName(), (StaffMember) teacher2, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(fencing.getName(), (StaffMember) teacher2, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(fencing.getName(), (StaffMember) teacher2, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};
        List<Session> sessionsBaseJump = new ArrayList<Session>(){{
            add(new Session(baseJump.getName(), (StaffMember) teacher3, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(baseJump.getName(), (StaffMember) teacher3, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(baseJump.getName(), (StaffMember) teacher3, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(baseJump.getName(), (StaffMember) teacher3, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};

        java.setSessions(sessionsJava);
        fencing.setSessions(sessionsFencing);
        baseJump.setSessions(sessionsBaseJump);
        java.addParticipants(this.personRepository.getAllPeople().stream().filter(CourseParticipant.class::isInstance).limit(2).map(CourseParticipant.class::cast).collect(Collectors.toList()));
        fencing.addParticipants(this.personRepository.getAllPeople().stream().filter(CourseParticipant.class::isInstance).skip(2).limit(2).map(CourseParticipant.class::cast).collect(Collectors.toList()));
        baseJump.addParticipants(this.personRepository.getAllPeople().stream().filter(CourseParticipant.class::isInstance).skip(4).map(CourseParticipant.class::cast).collect(Collectors.toList()));
    }

    @Override
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public void addCourses(List<Course> courses) {
        this.courses.addAll(courses);
    }

    @Override
    public Course findCourse(String name) {
        return this.courses.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Override
    public List<Course> findCourses(List<String> names) {
        return this.courses.stream().filter(c -> courses.contains(c.getName())).collect(Collectors.toList());
    }

    @Override
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    @Override
    public void removeCourses(List<Course> courses) {
        this.courses.removeAll(courses);
    }

}
