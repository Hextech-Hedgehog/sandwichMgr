package sandwich.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.SpringSandwichApplication;
import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringSandwichApplication.class)
class CourseMemoryRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findCourseFencingTest() throws CourseNotFoundException {
        assertEquals("Medieval fencing", courseRepository.findCourse("Medieval fencing").getName());
    }

    @Test
    public void findCourseJavaTest() throws CourseNotFoundException {
        assertEquals("Java Se programming", courseRepository.findCourse("Java Se programming").getName());
    }

    @Test
    public void findCourseBaseJumpingTest() throws CourseNotFoundException {
        assertEquals("Base jumping", courseRepository.findCourse("Base jumping").getName());
    }

    @Test
    public void findCourseThrowsCourseNotFoundExceptionTest() throws CourseNotFoundException {
        assertThrows(CourseNotFoundException.class, () -> courseRepository.findCourse(null));
    }

    @Test
    public void findCoursesByNamesWith3NamesInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Medieval fencing"); names.add("Java Se programming"); names.add("Base jumping");
        assertEquals(names, courseRepository.findCourses(names).stream().map(Course::getName).collect(Collectors.toList()));
    }

    @Test
    public void findCoursesByNamesWith2NamesInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Medieval fencing"); names.add("Java Se programming");
        assertEquals(names, courseRepository.findCourses(names).stream().map(Course::getName).collect(Collectors.toList()));
    }

    @Test
    public void findCoursesByNamesWith1NameInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Medieval fencing");
        assertEquals(names, courseRepository.findCourses(names).stream().map(Course::getName).collect(Collectors.toList()));
    }

    @Test
    public void findCoursesByNamesWithEmptyArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>(); // TODO check if this produces a NullPointerException or not?
        assertEquals(names, courseRepository.findCourses(names).stream().map(Course::getName).collect(Collectors.toList()));
    }

    @Test
    public void findCoursesWithOneFakeNameInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Medieval fencing"); names.add("Java Se programming"); names.add(null);
        assertThrows(CourseNotFoundException.class, () -> courseRepository.findCourses(names));
    }




}