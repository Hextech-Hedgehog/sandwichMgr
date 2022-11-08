package sandwich.repository;

import sandwich.model.User;
import sandwich.exception.PersonNotFoundException;

import java.util.List;

public interface PersonRepository {

    void addPerson(User user);
    void addPeople(List<User> users);
    User findPerson(String firstName) throws PersonNotFoundException;
    List<User> findPeople(List<String> firstNames) throws PersonNotFoundException;
    void removePerson(User user);
    void removePeople(List<User> users);
    List<User> getAllPeople();

}
