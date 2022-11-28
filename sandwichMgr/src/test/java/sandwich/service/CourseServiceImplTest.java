package sandwich.service;

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
class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @Mock private Course course;
    @Spy  private ArrayList<Course> courses;

    @Test
    public void findCourseFencingTest() throws CourseNotFoundException {
        assertEquals("Medieval fencing", courseService.findCourse("Medieval fencing").getName());
    }

    @Test
    public void findCourseJavaTest() throws CourseNotFoundException {
        assertEquals("Java SE programming", courseService.findCourse("Java SE programming").getName());
    }

    @Test
    public void findCourseBaseJumpingTest() throws CourseNotFoundException {
        assertEquals("Base jumping", courseService.findCourse("Base jumping").getName());
    }

    @Test
    public void findCourseNullCourseIllegalArgumentExceptionTest() throws CourseNotFoundException {
        assertThrows(IllegalArgumentException.class, () -> courseService.findCourse(null));
    }

    @Test
    public void findCoursesByNamesWith3NamesInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Medieval fencing"); names.add("Java SE programming"); names.add("Base jumping");
        assertTrue(courseService.findCourses(names).stream().map(Course::getName).collect(Collectors.toList()).containsAll(names));
    }

    @Test
    public void findCoursesByNamesWith2NamesInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Medieval fencing"); names.add("Java SE programming");
        assertTrue(courseService.findCourses(names).stream().map(Course::getName).collect(Collectors.toList()).containsAll(names));
    }

    @Test
    public void findCoursesByNamesWith1NameInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Medieval fencing");
        assertEquals(names, courseService.findCourses(names).stream().map(Course::getName).collect(Collectors.toList()));
    }

    @Test
    public void findCoursesByNamesWithEmptyArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();
        assertEquals(names, courseService.findCourses(names).stream().map(Course::getName).collect(Collectors.toList()));
    }

    @Test
    public void findCoursesWithNullNameInArrayTest() throws CourseNotFoundException {
        List<String> names = new ArrayList<>();;
        names.add("Medieval fencing"); names.add("Java SE programming"); names.add(null);
        assertThrows(CourseNotFoundException.class, () -> courseService.findCourses(names));
    }

    @Test
    void addCourseTest() {
        int size = courseService.getAllCourses().size();
        courseService.addCourse(course);
        assertEquals(size +1, courseService.getAllCourses().size());
    }

    @Test
    void addCoursesTest() {
        for (int i = 0; i < 4; i++) {
            courses.add(course);
        }
        int size = courseService.getAllCourses().size();
        courseService.addCourses(courses);
        assertEquals(size +4, courseService.getAllCourses().size());
    }

    @Test
    void  removeCourseTest() {
        courseService.addCourse(course);
        int size = courseService.getAllCourses().size();
        courseService.removeCourse(course);
        assertEquals(size -1, courseService.getAllCourses().size());
    }

    @Test
    void removeMultipleCourses() {
        for (int i = 0; i < 3; i++) {
            courses.add(course);
        }
        courseService.addCourses(courses);
        int size = courseService.getAllCourses().size();
        courseService.removeCourses(courses);
        assertEquals(size -3, courseService.getAllCourses().size());
    }

}