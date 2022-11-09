package sandwich.repository;

import org.springframework.stereotype.Repository;
import sandwich.model.User;
import sandwich.model.UserRole;
import sandwich.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserMemoryRepository implements UserRepository {

    Set<User> users = new HashSet<>();

    public UserMemoryRepository() {
        users.add(new User("Albus", "password", new HashSet<UserRole>(){{
            add(UserRole.TEACHER);
        }}));
        users.add(new User("Melanie", "password", new HashSet<UserRole>() {{
            add(UserRole.TEACHER);
        }}));
        users.add(new User("Jeff", "password", new HashSet<UserRole>() {{
            add(UserRole.TEACHER);
        }}));
        users.add(new User("Bob", "password", new HashSet<UserRole>(){{
            add(UserRole.ADMIN);
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
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void addUsers(Set<User> people) {
        this.users.addAll(people);
    }

    @Override
    public User findUser(String firstName) throws UserNotFoundException {
        return this.users.stream().filter(p -> p.getFirstName().equals(firstName)).findFirst().orElseThrow(()-> new UserNotFoundException("No user with this first name found"));
    }

    @Override
    public Set<User> findUsers(Set<String> firstNames) throws UserNotFoundException {
        // users.forEach(user -> findUser(user.getFirstName())); // requires a try-catch
        if (users.stream().filter(user -> firstNames.contains(user.getFirstName())).count() != firstNames.size()) {
            throw new UserNotFoundException("At least one of the users passed was not found");
        }
        return this.users.stream().filter(p -> firstNames.contains(p.getFirstName())).collect(Collectors.toSet());
    }

    @Override
    public void removeUser(User user) {
        this.users.remove(user);
    }

    @Override
    public void removeUsers(Set<User> people) {
        this.users.removeAll(people);
    }

    @Override
    public Set<User> getAllUsers() {
        return this.users;
    }
}
