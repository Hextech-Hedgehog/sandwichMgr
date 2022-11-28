package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.*;
import sandwich.exception.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;


@Service
@Profile("production")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        this.userRepository.addUser(user);
    }

    public void addUsers(List<User> users) {
        this.userRepository.addUsers(users);
    }

    @Override
    public User findUser(String firstName) throws UserNotFoundException {
        return this.userRepository.findUser(firstName);
    }

    @Override
        public List<User> findUsers(List<String> firstNames) throws UserNotFoundException, IllegalArgumentException {
        return this.userRepository.findUsers(firstNames);
    }

    @Override
    public void removeUser(User user) {
        this.userRepository.removeUser(user);
    }

    @Override
    public void removeUsers(List<User> users) {
        this.userRepository.removeUsers(users);
    }


    @Override
    public List<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    @Override
    public Order getOrderByUserForCurrentCourseSession(User user) throws CourseNotFoundException, SessionNotFoundException {
        if (user.getCourse() == null)
            throw new CourseNotFoundException(user.getFirstName() + " isn't sign in any course.");

        return user.getCourse().getSessionByDate(LocalDate.now()).getDailyOrder();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
