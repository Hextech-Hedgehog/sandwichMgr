package sandwich.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.entities.Order;
import sandwich.model.entities.User;

import java.util.List;


public interface UserService extends UserDetailsService {

    void addUser(User user);
    void addUsers(List<User> users);
    User findUserByMail(String email);
    void removeUser(User user);
    void removeUsers(List<User> users);
    List<User> getAllUsers();
    Order getOrderByUserForCurrentCourseSession(User user) throws CourseNotFoundException, SessionNotFoundException;

}
