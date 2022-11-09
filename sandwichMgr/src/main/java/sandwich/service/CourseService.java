package sandwich.service;

import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;
import java.util.Set;

public interface CourseService {

    void addCourse(Course course);
    void addCourses(Set<Course> courses);
    Course findCourse(String name) throws CourseNotFoundException;
    Set<Course> findCourses(Set<String> names) throws CourseNotFoundException;
    void removeCourse(Course course);
    void removeCourses(Set<Course> courses);

}
