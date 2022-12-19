package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.model.dto.CourseDTO;
import sandwich.model.entities.Course;
import sandwich.model.entities.Session;
import sandwich.model.entities.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {
    public static CourseDTO toDto(Course course) {
        return new CourseDTO(course.getCourseId(), course.getName(), course.getCourseParticipants().stream().map(UserMapper::toDto).collect(Collectors.toList()), course.getSessions().stream().map(SessionMapper::toDto).collect(Collectors.toList()));
    }

    public static Course toCourse(CourseDTO course) {
        List<User> users = course.getCourseParticipants().stream().map(UserMapper::toUser).collect(Collectors.toList());
        List<Session> sessions = course.getSessions().stream().map(SessionMapper::toSession).collect(Collectors.toList());
        return new Course(course.getName(), users, sessions);
    }
}
