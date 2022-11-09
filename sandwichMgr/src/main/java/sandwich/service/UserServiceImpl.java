package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.*;
import sandwich.exception.UserNotFoundException;
import sandwich.repository.UserRepository;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Profile("production")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void addPerson(User user) {
        this.userRepository.addUser(user);
    }

    @Override
    public void addPeople(Set<User> users) {
        this.userRepository.addUsers(users);
    }

    @Override
    public User findUser(String firstName) throws UserNotFoundException {
        return this.userRepository.findUser(firstName);
    }

    @Override
        public Set<User> findUsers(Set<String> firstNames) throws UserNotFoundException {
        return this.userRepository.findUsers(firstNames);
    }

    @Override
    public void removeUser(User user) {
        this.userRepository.removeUser(user);
    }

    @Override
    public void removeUser(Set<User> users) {
        this.userRepository.removeUsers(users);
    }


    @Override
    public Set<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    @Override
    @RolesAllowed("ADMIN")
    public Order getOrderByUserForCurrentCourseSession(User user) {
        if (user.getCourse() == null)
            return null;

        Order order = null;
        try {
            order = user.getCourse().getSessionByDate(LocalDate.now()).getDailyOrder();
        } catch (SessionNotFoundException e) {
            //LogManager.getLogger("error").error("bubu");
        }
        return order;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
