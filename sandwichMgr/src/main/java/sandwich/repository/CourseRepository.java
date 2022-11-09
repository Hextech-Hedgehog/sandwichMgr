package sandwich.repository;

import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;

import java.util.Set;

public interface CourseRepository {

    void addCourse(Course course);
    void addCourses(Set<Course> courses);
    Course findCourseByName(String name) throws CourseNotFoundException;
    Set<Course> findCoursesByName(Set<String> names) throws CourseNotFoundException;
    void removeCourse(Course course);
    void removeCourses(Set<Course> courses);

}
