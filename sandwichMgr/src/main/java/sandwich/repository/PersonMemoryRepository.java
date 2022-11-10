package sandwich.repository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import sandwich.model.User;
import sandwich.model.UserRole;
import sandwich.exception.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PersonMemoryRepository implements PersonRepository {

    List<User> users = new ArrayList<>();

    public PersonMemoryRepository() {
        users.add(new User("Albus", "password", new HashSet<UserRole>(){{
            add(UserRole.ADMIN);
        }}));
        users.add(new User("Bob", "password", new HashSet<UserRole>(){{
            add(UserRole.USER);
        }}));
        List<User> participants = new ArrayList<User>(){{
            add(new User("Harry"));
            add(new User("Peter"));
            add(new User("Mary"));
            add(new User("Gwen"));
            add(new User("Charlie"));
            add(new User("Archibald"));
        }};
        users.addAll(participants);
    }

    @Override
    public void addPerson(User user) {
        this.users.add(user);
    }

    @Override
    public void addPeople(List<User> people) {
        this.users.addAll(people);
    }

    @Override
    public User findUserByName(String firstName) throws PersonNotFoundException {
        return this.users.stream().filter(p -> p.getFirstName().equals(firstName)).findFirst().orElseThrow(()-> new PersonNotFoundException("No person with this first name found"));
    }

    @Override
    public User findUser(User user) throws PersonNotFoundException {
        return this.users.stream().filter(u -> u.equals(user)).findFirst().orElseThrow(() -> new PersonNotFoundException("Person not found"));
    }

    @Override
    public List<User> findPeople(List<String> firstNames) throws PersonNotFoundException {
        return this.users.stream().filter(p -> firstNames.contains(p.getFirstName())).collect(Collectors.toList());
    }

    @Override
    public void removePerson(User user) {
        this.users.remove(user);
    }

    @Override
    public void removePeople(List<User> people) {
        this.users.removeAll(people);
    }

    @Override
    public List<User> getAllPeople() {
        return this.users;
    }
}
