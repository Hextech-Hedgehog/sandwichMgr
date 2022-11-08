package sandwich.repository;

import sandwich.model.User;

import java.util.List;

public interface PersonRepository {

    void addPerson(User user);
    void addPeople(List<User> users);
    User findPerson(String firstName);
    List<User> findPeople(List<String> firstNames);
    void removePerson(User user);
    void removePeople(List<User> users);
    List<User> getAllPeople();

}
