package sandwich.repository;

import sandwich.model.Course;

import java.util.List;

public interface CourseRepository {

    void addCourse(Course course);
    void addCourses(List<Course> courses);
    Course findCourse(String name);
    List<Course> findCourses(List<String> names);
    void removeCourse(Course course);
    void removeCourses(List<Course> courses);

}
