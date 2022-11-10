package sandwich.repository;

import org.springframework.stereotype.Repository;
import sandwich.model.User;
import sandwich.model.UserRole;
import sandwich.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserMemoryRepository implements UserRepository {

    List<User> users = new ArrayList<>();

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
    public void addUser(User user) throws IllegalArgumentException {
        if (user == null) throw new IllegalArgumentException("Null-object has been passed");
        this.users.add(user);
    }

    @Override
    public void addUsers(List<User> users) throws IllegalArgumentException {
        if (users.contains(null)) throw new IllegalArgumentException("Null-object was passed along this lisit of users");
        this.users.addAll(users);
    }

    @Override
    public User findUser(String firstName) throws UserNotFoundException {
        if (firstName == null) throw new IllegalArgumentException("No name was passed");
        return this.users.stream().filter(p -> p.getFirstName().equals(firstName)).findFirst().orElseThrow(()-> new UserNotFoundException("No user with this first name found"));
    }

    @Override
    public List<User> findUsers(List<String> firstNames) throws UserNotFoundException, IllegalArgumentException {
        // users.forEach(user -> findUser(user.getFirstName())); // requires a try-catch
        if (firstNames.contains(null)) throw new IllegalArgumentException("No name passed");  // TODO
        if (users.stream().filter(user -> firstNames.contains(user.getFirstName())).count() != firstNames.size()) {
            throw new UserNotFoundException("At least one of the users passed was not found");
        }
        return this.users.stream().filter(p -> firstNames.contains(p.getFirstName())).collect(Collectors.toList());
    }

    @Override
    public void removeUser(User user) {
        if (user == null) throw new IllegalArgumentException("Null-object has been passed");
        this.users.remove(user);
    }

    @Override
    public void removeUsers(List<User> users) {
        this.users.removeAll(users);
    }

    @Override
    public List<User> getAllUsers() {
        return this.users;
    }
}
