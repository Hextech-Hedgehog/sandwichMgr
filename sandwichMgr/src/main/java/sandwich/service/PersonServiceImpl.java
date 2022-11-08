package sandwich.service;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.*;
import sandwich.exception.PersonNotFoundException;
import sandwich.repository.PersonRepository;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.List;

@Service
@Profile("production")
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    @Override
    public void addPerson(User person) {
        this.personRepository.addPerson(person);
    }

    @Override
    public void addPeople(List<User> people) {
        this.personRepository.addPeople(people);
    }

    @Override
    public User findPerson(String firstName) throws PersonNotFoundException {
        return this.personRepository.findPerson(firstName);
    }

    @Override
        public List<User> findPeople(List<String> firstNames) throws PersonNotFoundException {
        return this.personRepository.findPeople(firstNames);
    }

    @Override
    public void removePerson(User person) {
        this.personRepository.removePerson(person);
    }

    @Override
    public void removePeople(List<User> people) {
        this.personRepository.removePeople(people);
    }

    @Override
    public List<User> getAllPeople() {
        return this.personRepository.getAllPeople();
    }

    @Override
    @RolesAllowed("ADMIN")
    public Order getOrderByUserForCurrentCourseSession(User user) {
        if (user.getCourse() == null)
            return null;

        Order order = null;
        try {
            order = user.getCourse().getSessionByDate(LocalDate.now()).getDailyOrder();
        } catch (SessionNotFoundException e) {
            //LogManager.getLogger("error").error("bubu");
        }
        return order;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

}
