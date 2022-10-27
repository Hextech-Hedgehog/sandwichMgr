package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;
import sandwich.repository.CourseRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Override
    public void addCourse(Course course) {
        this.courseRepository.addCourse(course);
    }

    @Override
    public void addCourses(List<Course> courses) {
        this.courseRepository.addCourses(courses);
    }

    @Override
    public Course findCourse(String name) throws CourseNotFoundException {
        return this.courseRepository.findCourse(name);
    }

    @Override
    public List<Course> findCourses(List<String> names) {
        return this.courseRepository.findCourses(names);
    }

    @Override
    public void removeCourse(Course course) {
        this.courseRepository.removeCourse(course);
    }

    @Override
    public void removeCourses(List<Course> courses) {
        this.courseRepository.removeCourses(courses);
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
