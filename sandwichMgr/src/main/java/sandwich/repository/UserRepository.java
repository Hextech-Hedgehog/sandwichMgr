package sandwich.repository;

import sandwich.model.User;
import sandwich.exception.UserNotFoundException;

import java.util.Set;

public interface UserRepository {

    void addUser(User user);
    void addUsers(Set<User> users);
    User findUser(String firstName) throws UserNotFoundException;
    Set<User> findUsers(Set<String> firstNames) throws UserNotFoundException;
    void removeUser(User user);
    void removeUsers(Set<User> users);
    Set<User> getAllUsers();

}
