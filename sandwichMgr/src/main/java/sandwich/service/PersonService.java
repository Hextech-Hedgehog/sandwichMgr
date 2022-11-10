package sandwich.service;

import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.Order;
import sandwich.model.User;
import sandwich.exception.PersonNotFoundException;

import java.util.List;

public interface PersonService {

    void addPerson(User user);
    void addPeople(List<User> users);
    User findUser(User user) throws PersonNotFoundException;
    User findUserByName(String firstName) throws PersonNotFoundException;
    List<User> findUsers(List<String> firstNames) throws PersonNotFoundException;
    void removePerson(User user);
    void removePeople(List<User> users);
    List<User> getAllPeople();
    Order getOrderByUserForCurrentCourseSession(User user) throws SessionNotFoundException, CourseNotFoundException;

}
