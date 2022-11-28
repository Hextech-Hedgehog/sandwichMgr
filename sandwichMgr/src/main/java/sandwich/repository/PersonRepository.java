package sandwich.repository;

import sandwich.model.User;
import sandwich.exception.PersonNotFoundException;

import java.util.List;

public interface PersonRepository {

    void addPerson(User user);
    void addPeople(List<User> users);
    User findUserByName(String firstName) throws PersonNotFoundException;
    User findUser(User user) throws PersonNotFoundException;
    User findUser(String username, String password) throws PersonNotFoundException;
    User findUserById(int userId) throws PersonNotFoundException;
    List<User> findPeople(List<String> firstNames) throws PersonNotFoundException;
    void removePerson(User user);
    void removePeople(List<User> users);
    List<User> getAllPeople();
    String getKeyByUserId(int userId);
    void login(int userId);

}
