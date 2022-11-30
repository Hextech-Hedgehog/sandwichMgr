package sandwich.service;

import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.Order;
import sandwich.model.User;
import sandwich.exception.UserNotFoundException;

import java.util.List;


public interface UserService {

    void addUser(User user);
    void addUsers(List<User> users);
    User findUser(String firstName) throws UserNotFoundException;
    void removeUser(User user);
    void removeUsers(List<User> users);
    List<User> getAllUsers();
    Order getOrderByUserForCurrentCourseSession(User user) throws CourseNotFoundException, SessionNotFoundException;

}
