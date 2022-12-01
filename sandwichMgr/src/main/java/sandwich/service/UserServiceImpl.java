package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.*;
import sandwich.exception.UserNotFoundException;
import sandwich.repository.UserJpaRepository;

import java.time.LocalDate;
import java.util.List;


@Service
@Profile("production")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJpaRepository userRepository;

    @Override
    public void addUser(User user) {
        this.userRepository.save(user);
    }

    public void addUsers(List<User> users) {
        this.userRepository.saveAll(users);
    }

    @Override
    public User findUser(String firstName) throws UserNotFoundException {
        return this.userRepository.findByFirstName(firstName);
    }

    @Override
    public void removeUser(User user) {
        this.userRepository.delete(user);
    }


    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Order getSandwichOrderByUserForCurrentCourseSession(User user) throws CourseNotFoundException, SessionNotFoundException {
        if (user.getCourse() == null)
            throw new CourseNotFoundException(user.getFirstName() + " isn't signed in any course.");

        return user.getCourse().getSessionByDate(LocalDate.now()).getDailyOrder();  // TODO
    }

/*    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }  */

}
