package sandwich.service;

import sandwich.model.Order;
import sandwich.model.User;
import sandwich.exception.PersonNotFoundException;

import java.util.List;

public interface PersonService {

    void addPerson(User person);
    void addPeople(List<User> people);
    User findPerson(String firstName) throws PersonNotFoundException;
    List<User> findPeople(List<String> firstNames) throws PersonNotFoundException;
    void removePerson(User person);
    void removePeople(List<User> people);
    List<User> getAllPeople();
    Order getOrderByUserForCurrentCourseSession(User person);

}
