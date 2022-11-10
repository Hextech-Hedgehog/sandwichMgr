package sandwich.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
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

    @Mock private Course course;
    @Spy  private ArrayList<Course> courses;


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
    public void findCourseNullCourseIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> courseRepository.findCourseByName(null));
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

    @Test
    void addCourseTest() {
        int size = courseRepository.getAllCourses().size();
        courseRepository.addCourse(course);
        assertEquals(size +1, courseRepository.getAllCourses().size());
    }

    @Test
    void addCoursesTest() {
        for (int i = 0; i < 4; i++) {
            courses.add(course);
        }
        int size = courseRepository.getAllCourses().size();
        courseRepository.addCourses(courses);
        assertEquals(size +4, courseRepository.getAllCourses().size());
    }

    @Test
    void  removeCourseTest() {
        courseRepository.addCourse(course);
        int size = courseRepository.getAllCourses().size();
        courseRepository.removeCourse(course);
        assertEquals(size -1, courseRepository.getAllCourses().size());
    }

    @Test
    void removeMultipleCourses() {
        for (int i = 0; i < 3; i++) {
            courses.add(course);
        }
        courseRepository.addCourses(courses);
        int size = courseRepository.getAllCourses().size();
        courseRepository.removeCourses(courses);
        assertEquals(size -3, courseRepository.getAllCourses().size());
    }




}