package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.entities.Order;
import sandwich.model.entities.User;
import sandwich.repository.UserJpaRepository;

import java.time.LocalDate;
import java.util.List;


@Service
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
    public User findUserByMail(String email) {
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
    public User getUserById(int userId) {
        return this.userRepository.getById(userId);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = this.userRepository.findByUsername(username);
        if (userDetails == null)
            throw new IllegalArgumentException("User does not exist!");
        return userDetails;
    }
}