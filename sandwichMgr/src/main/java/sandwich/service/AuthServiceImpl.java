package sandwich.service;

import org.apache.logging.log4j.LogManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sandwich.config.jwt.JwtTokenProvider;
import sandwich.exception.UserNotFoundException;
import sandwich.mapper.UserMapper;
import sandwich.model.dto.UserDTO;
import sandwich.model.entities.User;
import sandwich.model.forms.user.UserLoginForm;
import sandwich.model.forms.user.UserRegisterForm;
import sandwich.repository.UserJpaRepository;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;
    private final UserJpaRepository repository;
    private final UserMapper mapper;
    private final JwtTokenProvider provider;
    private AuthenticationManager manager;

    public AuthServiceImpl(PasswordEncoder encoder, UserJpaRepository repository, UserMapper mapper,
                           JwtTokenProvider provider, AuthenticationManager manager) {
        this.encoder = encoder;
        this.repository = repository;
        this.mapper = mapper;
        this.provider = provider;
        this.manager = manager;
    }

    @Override
    public UserDTO login(UserLoginForm form) throws UserNotFoundException {
        try {
            User u = repository.findByUsername(form.getUsername());
            if (u == null)
                throw new UserNotFoundException("User does not exist!");

            manager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
            UserDTO dto = mapper.toDto(u);
            System.out.println(provider.createToken(u));
            dto.setToken(provider.createToken(u));

            return dto;
        } catch (Exception e) {
            LogManager.getLogger("exceptionLogger").error(e.getMessage());
            throw new UserNotFoundException("Incorrect credentials!");
        }
    }

    @Override
    public UserDTO register(UserRegisterForm form) throws UserNotFoundException {
        User u = new User();

        u = mapper.formToUser(form, u);
        UserDTO dto = mapper.toDto(repository.save(u));
        dto.setToken(provider.createToken(u));

        return dto;
    }

}
