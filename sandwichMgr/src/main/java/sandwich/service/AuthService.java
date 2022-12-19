package sandwich.service;

import sandwich.exception.UserNotFoundException;
import sandwich.model.dto.UserDTO;
import sandwich.model.forms.user.UserLoginForm;
import sandwich.model.forms.user.UserRegisterForm;

public interface AuthService {
    UserDTO login(UserLoginForm form) throws UserNotFoundException;
    UserDTO register(UserRegisterForm form) throws UserNotFoundException;
}
