package sandwich.service;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.*;
import sandwich.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Profile("production")
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

    @Override
    public Order getOrderByCurrentCourseSession(Person person) {
        if (!(person instanceof CourseParticipant) || ((CourseParticipant)person).getCourse() == null)
            return null;

        Order order = null;
        try {
            order = ((CourseParticipant)person).getCourse().getSessionByDate(LocalDate.now()).getDailyOrder();
        } catch (SessionNotFoundException e) {
            LogManager.getLogger("error").error("bubu");
        }
        return order;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

}
