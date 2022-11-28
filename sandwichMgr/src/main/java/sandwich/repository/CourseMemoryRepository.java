package sandwich.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.PersonNotFoundException;
import sandwich.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
        User teacher = null;
        User teacher2 = null;
        User teacher3 = null;

        try {
            teacher = personRepository.findUserById(1);
            teacher2 = personRepository.findUserById(3);
            teacher3 = personRepository.findUserById(4);
        } catch (PersonNotFoundException e) {
            //LogManager.getLogger("error").error("tihi" + e.getMessage());
        }

        List<Session> sessionsJava = new ArrayList<>();
        sessionsJava.add(new Session(java.getName(), teacher, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
        sessionsJava.add(new Session(java.getName(), teacher, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
        sessionsJava.add(new Session(java.getName(),  teacher, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
        sessionsJava.add(new Session(java.getName(),  teacher, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));

        List<Session> sessionsFencing = new ArrayList<>();
        sessionsFencing.add(new Session(fencing.getName(), teacher2, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
        sessionsFencing.add(new Session(fencing.getName(), teacher2, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
        sessionsFencing.add(new Session(fencing.getName(),  teacher2, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
        sessionsFencing.add(new Session(fencing.getName(),  teacher2, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));

        List<Session> sessionsBaseJump = new ArrayList<>();
        sessionsBaseJump.add(new Session(baseJump.getName(),teacher3, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
        sessionsBaseJump.add(new Session(baseJump.getName(),  teacher3, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
        sessionsBaseJump.add(new Session(baseJump.getName(), teacher3, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
        sessionsBaseJump.add(new Session(baseJump.getName(), teacher3, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));

        java.setSessions(sessionsJava);
        fencing.setSessions(sessionsFencing);
        baseJump.setSessions(sessionsBaseJump);
        java.addParticipants(this.personRepository.getAllPeople().stream().filter(u -> u.getAuthorities().contains(UserRole.USER)).limit(2).collect(Collectors.toList()));
        fencing.addParticipants(this.personRepository.getAllPeople().stream().filter(u -> u.getAuthorities().contains(UserRole.USER)).skip(2).limit(2).collect(Collectors.toList()));
        baseJump.addParticipants(this.personRepository.getAllPeople().stream().filter(u -> u.getAuthorities().contains(UserRole.USER)).skip(4).collect(Collectors.toList()));
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
    public Course findCourse(String name) throws CourseNotFoundException {
        return this.courses.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst()
                .orElseThrow(() -> new CourseNotFoundException("No course found with this name"));
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
