package sandwich.service;

import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;

import java.util.List;


public interface CourseService {

    void addCourse(Course course);
    void addCourses(List<Course> courses);
    Course findCourse(String name) throws CourseNotFoundException;
    void removeCourse(Course course);
    void removeCourses(List<Course> courses);
    List<Course> getAllCourses();

}
