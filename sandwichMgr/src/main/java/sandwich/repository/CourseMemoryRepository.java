package sandwich.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sandwich.exception.CourseNotFoundException;
import sandwich.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseMemoryRepository implements CourseRepository {

    private List<Course> courses = new ArrayList<>();
    private UserRepository personRepository;

    public CourseMemoryRepository(@Autowired UserRepository personRepository) {
        this.personRepository = personRepository;
        Course java = new Course("Java Se programming");
        Course fencing = new Course("Medieval fencing");
        Course baseJump = new Course("Base jumping");
        User teacher = new User("Albus", "password", new HashSet<UserRole>() {{
            add(UserRole.TEACHER);
        }});
        User teacher2 = new User("Jeff", "password", new HashSet<UserRole>() {{
            add(UserRole.TEACHER);
        }});
        User teacher3 = new User("Melene", "password", new HashSet<UserRole>() {{
            add(UserRole.TEACHER);
        }});

        List<Session> sessionsJava = new ArrayList<Session>(){{
            add(new Session(java.getName(), teacher, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(java.getName(), teacher, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(java.getName(),  teacher, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(java.getName(),  teacher, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};
        List<Session> sessionsFencing = new ArrayList<Session>(){{
            add(new Session(fencing.getName(), teacher2, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(fencing.getName(), teacher2, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(fencing.getName(),  teacher2, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(fencing.getName(),  teacher2, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};
        List<Session> sessionsBaseJump = new ArrayList<Session>(){{
            add(new Session(baseJump.getName(),teacher3, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(baseJump.getName(),  teacher3, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(baseJump.getName(), teacher3, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(baseJump.getName(), teacher3, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};

        java.setSessions(sessionsJava);
        fencing.setSessions(sessionsFencing);           // TODO this.personRepository is null
        baseJump.setSessions(sessionsBaseJump);
        java.addParticipants(this.personRepository.getAllUsers().stream().filter(u -> u.getAuthorities().contains(UserRole.USER)).limit(2).collect(Collectors.toList()));
        fencing.addParticipants(this.personRepository.getAllUsers().stream().filter(u -> u.getAuthorities().contains(UserRole.USER)).skip(2).limit(2).collect(Collectors.toList()));
        baseJump.addParticipants(this.personRepository.getAllUsers().stream().filter(u -> u.getAuthorities().contains(UserRole.USER)).skip(4).collect(Collectors.toList()));
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
