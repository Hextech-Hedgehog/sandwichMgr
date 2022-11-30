package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.dto.CourseDTO;
import sandwich.model.Course;
import sandwich.model.Session;
import sandwich.model.User;

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
