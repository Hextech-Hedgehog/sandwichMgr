package sandwich.repository;

import sandwich.model.Person;

import java.util.List;

public interface PersonRepository {

    void addPerson(Person person);
    void addPeople(List<Person> people);
    Person findPerson(String firstName);
    List<Person> findPeople(List<String> firstNames);
    void removePerson(Person person);
    void removePeople(List<Person> people);
    List<Person> getAllPeople();

}
