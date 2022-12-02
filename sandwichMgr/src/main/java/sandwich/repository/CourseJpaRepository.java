package sandwich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sandwich.model.Course;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course, Integer> {
    @Query(value="select * from course where cname=:name", nativeQuery = true)
    Course findCourseByName(@Param("name") String name);
}