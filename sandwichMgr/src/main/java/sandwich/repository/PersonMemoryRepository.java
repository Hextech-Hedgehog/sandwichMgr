package sandwich.repository;

import org.springframework.stereotype.Repository;
import sandwich.exception.PersonNotFoundException;
import sandwich.model.Admin;
import sandwich.model.CourseParticipant;
import sandwich.model.Person;
import sandwich.model.StaffMember;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonMemoryRepository implements PersonRepository {

    List<Person> people = new ArrayList<>();

    public PersonMemoryRepository() {
        people.add(new StaffMember("Albus"));
        people.add(new Admin("Bob"));
        List<CourseParticipant> participants = new ArrayList<CourseParticipant>(){{
            add(new CourseParticipant("Harry"));
            add(new CourseParticipant("Peter"));
            add(new CourseParticipant("Mary"));
            add(new CourseParticipant("Gwen"));
            add(new CourseParticipant("Charlie"));
            add(new CourseParticipant("Archibald"));
        }};
        people.addAll(participants);
    }

    @Override
    public void addPerson(Person person) {
        this.people.add(person);
    }

    @Override
    public void addPeople(List<Person> people) {
        this.people.addAll(people);
    }

    @Override
    public Person findPerson(String firstName) throws PersonNotFoundException {
        return this.people.stream().filter(p -> p.getFirstName().equals(firstName)).findFirst()
                .orElseThrow( ()-> new PersonNotFoundException("No person with this first name found"));
    }

    @Override
    public List<Person> findPeople(List<String> firstNames) throws PersonNotFoundException {
        return this.people.stream().filter(p -> firstNames.contains(p.getFirstName())).collect(Collectors.toList());
    }

    @Override
    public void removePerson(Person person) {
        this.people.remove(person);
    }

    @Override
    public void removePeople(List<Person> people) {
        this.people.removeAll(people);
    }

    @Override
    public List<Person> getAllPeople() {
        return this.people;
    }
}
