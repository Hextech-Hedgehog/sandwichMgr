package sandwich.service;

import sandwich.exception.PersonNotFoundException;
import sandwich.model.Person;

import java.util.List;

public interface PersonService {

    void addPerson(Person person);
    void addPeople(List<Person> people);
    Person findPerson(String firstName) throws PersonNotFoundException;
    List<Person> findPeople(List<String> firstNames) throws PersonNotFoundException;
    void removePerson(Person person);
    void removePeople(List<Person> people);
    List<Person> getAllPeople();

}
