package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandwich.model.Person;
import sandwich.repository.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    @Override
    public void addPerson(Person person) {
        this.personRepository.addPerson(person);
    }

    @Override
    public void addPeople(List<Person> people) {
        this.personRepository.addPeople(people);
    }

    @Override
    public Person findPerson(String firstName) {
        return this.personRepository.findPerson(firstName);
    }

    @Override
    public List<Person> findPeople(List<String> firstNames) {
        return this.personRepository.findPeople(firstNames);
    }

    @Override
    public void removePerson(Person person) {
        this.personRepository.removePerson(person);
    }

    @Override
    public void removePeople(List<Person> people) {
        this.personRepository.removePeople(people);
    }

    @Override
    public List<Person> getAllPeople() {
        return this.personRepository.getAllPeople();
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

}
