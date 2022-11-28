package sandwich.repository;

import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;

import java.util.List;


public interface CourseRepository {

    void addCourse(Course course);
    void addCourses(List<Course> courses);
    Course findCourseByName(String name) throws CourseNotFoundException;
    List<Course> findCoursesByName(List<String> names) throws CourseNotFoundException;
    void removeCourse(Course course);
    void removeCourses(List<Course> courses);
    List<Course> getAllCourses();

}
