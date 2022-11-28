package sandwich.repository;

import org.springframework.stereotype.Repository;
import sandwich.model.User;
import sandwich.model.UserRole;
import sandwich.exception.PersonNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PersonMemoryRepository implements PersonRepository {

    private List<User> users = new ArrayList<>();
    private Map<Integer, String> apiKeys = new HashMap<>();

    public PersonMemoryRepository() {
        users.add(new User(1, "Albus", "password", new HashSet<UserRole>(){{
            add(UserRole.ADMIN);
            add(UserRole.TEACHER);
        }}));
        users.add(new User(2, "Bob", "password", new HashSet<UserRole>(){{
            add(UserRole.USER);
        }}));
        users.add(new User(3, "Jeff", "Hardy", new HashSet<UserRole>(){{
            add(UserRole.TEACHER);
        }}));
        users.add(new User(4, "Melene", "Eatyourvegetables45", new HashSet<UserRole>(){{
            add(UserRole.TEACHER);
        }}));
        List<User> participants = new ArrayList<User>(){{
            add(new User(5, "Harry"));
            add(new User(6, "Peter"));
            add(new User(7, "Mary"));
            add(new User(8, "Gwen"));
            add(new User(9, "Charlie"));
            add(new User(10, "Archibald"));
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
    public User findUser(String username, String password) throws PersonNotFoundException {
        return this.users.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst().orElseThrow(() -> new PersonNotFoundException("No user found with this combination of credentials"));
    }

    @Override
    public User findUserById(int userId) throws PersonNotFoundException {
        return this.users.stream().filter(u -> u.getUserId() == userId).findFirst().orElseThrow(() -> new PersonNotFoundException("Person not found"));
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

    @Override
    public String getKeyByUserId(int userId) {
        return this.apiKeys.get(userId);
    }

    @Override
    public void login(int userId) {
        this.generateApiKey(userId);
    }

    private void generateApiKey(int personId) {
        this.apiKeys.put(personId, UUID.randomUUID().toString());
    }

}
