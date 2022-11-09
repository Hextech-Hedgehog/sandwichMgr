package sandwich.service;

import sandwich.model.Order;
import sandwich.model.User;
import sandwich.exception.UserNotFoundException;

import java.util.Set;

public interface UserService {

    void addPerson(User person);
    void addPeople(Set<User> people);
    User findUser(String firstName) throws UserNotFoundException;
    Set<User> findUsers(Set<String> firstNames) throws UserNotFoundException;
    void removeUser(User person);
    void removeUser(Set<User> people);
    Set<User> getAllUsers();
    Order getOrderByUserForCurrentCourseSession(User person);

}
