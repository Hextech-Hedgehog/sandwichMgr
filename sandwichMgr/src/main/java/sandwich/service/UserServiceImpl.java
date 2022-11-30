package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.*;
import sandwich.exception.UserNotFoundException;
import sandwich.utils.repository.UserJpaRepository;

import java.time.LocalDate;
import java.util.List;


@Service
@Profile("production")
public class UserServiceImpl implements UserService {

    private UserJpaRepository userRepository;

    @Override
    public void addUser(User user) {
        this.userRepository.save(user);
    }

    public void addUsers(List<User> users) {
        this.userRepository.saveAll(users);
    }

    //TODO Exception handling
    @Override
    public User findUser(String email) throws UserNotFoundException {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public void removeUser(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public void removeUsers(List<User> users) {
        this.userRepository.deleteAll(users);
    }


    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Order getOrderByUserForCurrentCourseSession(User user) throws CourseNotFoundException, SessionNotFoundException {
        if (user.getCourse() == null)
            throw new CourseNotFoundException(user.getFirstName() + " isn't sign in any course.");

        return user.getCourse().getSessions().stream().filter(s -> s.coversDate(LocalDate.now())).findFirst().orElseThrow(() -> new SessionNotFoundException("No session found at this date: " + LocalDate.now())).getDailyOrder();
    }

    @Autowired
    public void setUserRepository(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

}
