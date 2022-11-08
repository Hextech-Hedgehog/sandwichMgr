package sandwich.service;

import sandwich.model.Order;
import sandwich.model.User;
import sandwich.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    void addPerson(User person);
    void addPeople(List<User> people);
    User findUser(String firstName) throws UserNotFoundException;
    List<User> findUsers(List<String> firstNames) throws UserNotFoundException;
    void removeUser(User person);
    void removeUser(List<User> people);
    List<User> getAllUsers();
    Order getOrderByUserForCurrentCourseSession(User person);

}
