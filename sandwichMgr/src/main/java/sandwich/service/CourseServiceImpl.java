package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;
import sandwich.repository.CourseRepository;

import java.util.List;


@Service
@Profile("production")
public class CourseServiceImpl implements CourseService {

    @Autowired
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
        return this.courseRepository.findCourseByName(name);
    }

    @Override
    public List<Course> findCourses(List<String> names) throws CourseNotFoundException {
        return this.courseRepository.findCoursesByName(names);
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

    @Autowired
    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }
}
