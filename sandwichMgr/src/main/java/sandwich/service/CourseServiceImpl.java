package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;
import sandwich.utils.repository.CourseJpaRepository;

import java.util.List;


@Service
@Profile("production")
public class CourseServiceImpl implements CourseService {

    private CourseJpaRepository courseRepository;

    @Override
    public void addCourse(Course course) {
        this.courseRepository.save(course);
    }

    @Override
    public void addCourses(List<Course> courses) {
        this.courseRepository.saveAll(courses);
    }

    //TODO Add Exception handling
    @Override
    public Course findCourse(String name) throws CourseNotFoundException {
        return this.courseRepository.findCourseByName(name);
    }

    @Override
    public void removeCourse(Course course) {
        this.courseRepository.delete(course);
    }

    @Override
    public void removeCourses(List<Course> courses) {
        this.courseRepository.deleteAll(courses);
    }

    @Autowired
    public void setCourseRepository(CourseJpaRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
