package sandwich.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sandwich.model.dto.UserDTO;
import sandwich.model.entities.User;
import sandwich.model.forms.user.UserRegisterForm;

@Component
public class UserMapper {
    private final PasswordEncoder encoder;

    public UserMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public static UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public static User toUser(UserDTO user) {
        User u = new User(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword());
        u.setCredentialsNonExpired(user.isCredentialsNonExpired());
        u.setAccountNonExpired(user.isAccountNonExpired());
        u.setAccountNonLocked(user.isAccountNonLocked());
        u.setEnabled(user.isEnabled());
        return u;
    }

    public User formToUser(UserRegisterForm form, User user)
    {
        if(form != null)
        {
            user.setUsername(form.getUsername());
            user.setPassword(this.encoder.encode(form.getPassword()));
            user.setEmail(form.getEmail());
            user.setCredentialsNonExpired(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setEnabled(true);
        }

        return user;
    }
}
