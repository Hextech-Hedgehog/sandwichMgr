package sandwich.service;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
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
    public User findUserByName(String firstName) throws PersonNotFoundException {
        return this.personRepository.findUserByName(firstName);
    }

    @Override
    public User findUser(User user) throws PersonNotFoundException {
        return this.personRepository.findUser(user);
    }

    @Override
    public User findUser(String username, String password) throws PersonNotFoundException {
        return this.personRepository.findUser(username, password);
    }

    @Override
    public User findUserById(int userId) throws PersonNotFoundException {
        return this.personRepository.findUserById(userId);
    }

    @Override
        public List<User> findUsers(List<String> firstNames) throws PersonNotFoundException {
        return this.personRepository.findPeople(firstNames);
    }

    @Override
    public void removePerson(User user) {
        this.personRepository.removePerson(user);
    }

    @Override
    public void removePeople(List<User> users) {
        this.personRepository.removePeople(users);
    }

    @Override
    public List<User> getAllPeople() {
        return this.personRepository.getAllPeople();
    }

    @Override
    public Order getOrderByUserForCurrentCourseSession(User user) throws SessionNotFoundException, CourseNotFoundException {
        if (user.getCourse() == null)
            throw new CourseNotFoundException(user.getFirstName() + " isn't sign in any course.");

        return user.getCourse().getSessionByDate(LocalDate.now()).getDailyOrder();
    }

    @Override
    public String getKeyByUserId(int userId) {
        return this.personRepository.getKeyByUserId(userId);
    }

    @Override
    public void login(int userId) {
        this.personRepository.login(userId);
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

}
