package sandwich.service;

import sandwich.model.Order;
import sandwich.model.User;
import sandwich.exception.UserNotFoundException;

import java.util.List;


public interface UserService {

    void addUser(User user);
    void addUsers(List<User> users);
    User findUser(String firstName) throws UserNotFoundException;
    List<User> findUsers(List<String> firstNames) throws UserNotFoundException;
    void removeUser(User user);
    void removeUsers(List<User> users);
    List<User> getAllUsers();
    Order getOrderByUserForCurrentCourseSession(User user);

}
