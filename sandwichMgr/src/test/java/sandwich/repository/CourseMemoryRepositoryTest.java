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
    private CourseRepository courseRepository;

    @Test
    public void findCourseFencingTest() throws CourseNotFoundException {
        assertEquals("Medieval fencing", courseRepository.findCourseByName("Medieval fencing").getName());
    }

    @Test
    public void findCourseJavaTest() throws CourseNotFoundException {
        assertEquals("Java SE programming", courseRepository.findCourseByName("Java SE programming").getName());
    }

    @Test
    public void findCourseBaseJumpingTest() throws CourseNotFoundException {
        assertEquals("Base jumping", courseRepository.findCourseByName("Base jumping").getName());
    }

    @Test
    public void findCourseThrowsCourseNotFoundExceptionTest() {
        assertThrows(CourseNotFoundException.class, () -> courseRepository.findCourseByName(null));
    }

    @Test
    public void findCoursesByNamesWith3NamesInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Medieval fencing"); names.add("Java SE programming"); names.add("Base jumping");
        assertTrue(courseRepository.findCoursesByName(names).stream().map(Course::getName).collect(Collectors.toList()).containsAll(names));
    }

    @Test
    public void findCoursesByNamesWith2NamesInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Medieval fencing"); names.add("Java SE programming");
        assertTrue(courseRepository.findCoursesByName(names).stream().map(Course::getName).collect(Collectors.toList()).containsAll(names));
    }

    @Test
    public void findCoursesByNamesWith1NameInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Medieval fencing");
        assertEquals(names, courseRepository.findCoursesByName(names).stream().map(Course::getName).collect(Collectors.toList()));
    }

    @Test
    public void findCoursesByNamesWithEmptyArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        assertEquals(names, courseRepository.findCoursesByName(names).stream().map(Course::getName).collect(Collectors.toList()));
    }

    @Test
    public void findCoursesWithNullNameInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Medieval fencing"); names.add("Java SE programming"); names.add(null);
        assertThrows(CourseNotFoundException.class, () -> courseRepository.findCoursesByName(names));
    }

    @Test
    public void findCoursesWithFakeNameInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Medieval fencing"); names.add("Java SE programming"); names.add("Mastodon");
        assertThrows(CourseNotFoundException.class, () -> courseRepository.findCoursesByName(names));
    }




}