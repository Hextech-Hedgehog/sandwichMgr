package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;
import sandwich.repository.CourseRepository;

import java.util.List;
import java.util.Set;

@Service
@Profile("production")
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Override
    public void addCourse(Course course) {
        this.courseRepository.addCourse(course);
    }

    @Override
    public void addCourses(Set<Course> courses) {
        this.courseRepository.addCourses(courses);
    }

    @Override
    public Course findCourse(String name) throws CourseNotFoundException {
        return this.courseRepository.findCourseByName(name);
    }

    @Override
    public Set<Course> findCourses(Set<String> names) throws CourseNotFoundException {
        return this.courseRepository.findCoursesByName(names);
    }

    @Override
    public void removeCourse(Course course) {
        this.courseRepository.removeCourse(course);
    }

    @Override
    public void removeCourses(Set<Course> courses) {
        this.courseRepository.removeCourses(courses);
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
