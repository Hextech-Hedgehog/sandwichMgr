package sandwich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sandwich.exception.CourseNotFoundException;
import sandwich.model.Course;

import java.util.List;


public interface CourseJpaRepository extends JpaRepository<Course, Integer> {


    Course findByName (String name) throws CourseNotFoundException;

}
