package sandwich.service;

import sandwich.model.Order;
import sandwich.model.Person;

import java.util.List;

public interface PersonService {

    void addPerson(Person person);
    void addPeople(List<Person> people);
    Person findPerson(String firstName);
    List<Person> findPeople(List<String> firstNames);
    void removePerson(Person person);
    void removePeople(List<Person> people);
    List<Person> getAllPeople();
    Order getOrderByCurrentCourseSession(Person person);

}
