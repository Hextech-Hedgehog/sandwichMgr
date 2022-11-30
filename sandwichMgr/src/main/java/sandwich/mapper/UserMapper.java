package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.dto.UserDTO;
import sandwich.model.User;

@Component
public class UserMapper {
    public static UserDTO toDto(User user) {
        return new UserDTO(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), CourseMapper.toDto(user.getCourse()));
    }

    public static User toUser(UserDTO user) {
        return new User(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), CourseMapper.toCourse(user.getCourse()));
    }
}
