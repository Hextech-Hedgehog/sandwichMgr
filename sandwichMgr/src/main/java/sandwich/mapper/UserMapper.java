package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.dto.UserDTO;
import sandwich.model.User;

@Component
public class UserMapper {
    public static UserDTO toDto(User user) {
        return new UserDTO(user.getUserId(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public static User toUser(UserDTO user) {
        return new User(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword());
    }
}
