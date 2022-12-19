package sandwich.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sandwich.exception.UserNotFoundException;
import sandwich.model.dto.UserDTO;
import sandwich.model.forms.user.UserLoginForm;
import sandwich.model.forms.user.UserRegisterForm;
import sandwich.service.AuthService;

@RestController
public class AuthController {
    private AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid UserLoginForm form) throws UserNotFoundException {
        return ResponseEntity.ok(service.login(form));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserRegisterForm form) throws UserNotFoundException {
        return ResponseEntity.ok(service.register(form));
    }
}

