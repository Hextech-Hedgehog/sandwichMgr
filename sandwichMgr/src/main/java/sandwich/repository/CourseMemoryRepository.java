package sandwich.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.UserNotFoundException;
import sandwich.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseMemoryRepository implements CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public CourseMemoryRepository(@Autowired UserRepository userRepository) throws IllegalArgumentException, UserNotFoundException {
        Course java = new Course("Java SE programming");
        Course fencing = new Course("Medieval fencing");
        Course baseJump = new Course("Base jumping");

        User teacher1 = userRepository.findUser("Albus");
        User teacher2 = userRepository.findUser("Melanie");
        User teacher3 = userRepository.findUser("Jeff");

        List<Session> sessionsJava = new ArrayList<Session>(){{
            add(new Session(java.getName(), teacher1, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(java.getName(), teacher1, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(java.getName(), teacher1, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(java.getName(), teacher1, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};
        List<Session> sessionsFencing = new ArrayList<Session>(){{
            add(new Session(fencing.getName(), teacher2, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(fencing.getName(), teacher2, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(fencing.getName(), teacher2, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(fencing.getName(), teacher2, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};
        List<Session> sessionsBaseJump = new ArrayList<Session>(){{
            add(new Session(baseJump.getName(), teacher3, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(baseJump.getName(), teacher3, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(baseJump.getName(), teacher3, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(baseJump.getName(), teacher3, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};

        java.setSessions(sessionsJava);
        fencing.setSessions(sessionsFencing);
        baseJump.setSessions(sessionsBaseJump);
        java.addParticipants(userRepository.getAllUsers().stream().limit(2).collect(Collectors.toList()));
        fencing.addParticipants(userRepository.getAllUsers().stream().skip(2).limit(2).collect(Collectors.toList()));
        baseJump.addParticipants(userRepository.getAllUsers().stream().skip(4).collect(Collectors.toList()));

        courses.add(java); courses.add(fencing); courses.add(baseJump);
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
    public Course findCourseByName(String name) throws CourseNotFoundException, IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException("No name was passed");
        return this.courses.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst()
                .orElseThrow(() -> new CourseNotFoundException("No course found with this name"));
    }

    @Override
    public List<Course> findCoursesByName(List<String> names) throws CourseNotFoundException {
        if (courses.stream().filter(course -> names.contains(course.getName())).count() != names.size()) {
            throw new CourseNotFoundException("At least one of the courses passed was not found");
        }
        return this.courses.stream().filter(c -> names.contains(c.getName())).collect(Collectors.toList());

    }

    @Override
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    @Override
    public void removeCourses(List<Course> courses) {
        courses.forEach(this.courses::remove);
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }
}
