package sandwich.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.SpringSandwichApplication;
import sandwich.exception.UserNotFoundException;
import sandwich.model.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringSandwichApplication.class)
class UserMemoryRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findUserAlbusTest() throws UserNotFoundException {
        assertEquals("Albus", userRepository.findUser("Albus").getFirstName());
    }

    @Test
    public void findUserHarryTest() throws UserNotFoundException {
        assertEquals("Harry", userRepository.findUser("Harry").getFirstName());
    }

    @Test
    public void findUserArchibaldTest() throws UserNotFoundException {
        assertEquals("Archibald", userRepository.findUser("Archibald").getFirstName());
    }

    @Test
    public void findUserThrowsUserNotFoundExceptionTest() {
        assertThrows(UserNotFoundException.class, () -> userRepository.findUser(null));
    }

    @Test
    public void findUsersByNamesWith3NamesInArrayTest() throws UserNotFoundException {
        Set<String> names = new HashSet<>();         // TODO works better with a Set than a List
        names.add("Peter"); names.add("Mary"); names.add("Bob");
        assertEquals(names, userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toSet()));
    }       // TODO finds all three but in different order

    @Test
    public void findUsersByNamesWith2NamesInArrayTest() throws UserNotFoundException {
        Set<String> names = new HashSet<>();
        names.add("Charlie"); names.add("Gwen");
        assertEquals(names, userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toSet()));
    }           // TODO finds all two but in different order

    @Test
    public void findUsersByNamesWith1NameInArrayTest() throws UserNotFoundException {
        Set<String> names = new HashSet<>();
        names.add("Archibald");
        assertEquals(names, userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toSet()));
    }

    @Test
    public void findUsersByNamesWithEmptyArrayTest() throws UserNotFoundException {
        Set<String> names = new HashSet<>();
        assertEquals(names, userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toSet()));
    }

    @Test
    public void findUsersWithOneFakeNameInArrayTest() {
        Set<String> names = new HashSet<>();
        names.add("Bob"); names.add("Albus"); names.add("Mastodon");
        assertThrows(UserNotFoundException.class, () -> userRepository.findUsers(names));
    }

    @Test
    public void findUsersWithNullNameInArrayTest() {
        Set<String> names = new HashSet<>();
        names.add("Bob"); names.add("Albus"); names.add(null);
        assertThrows(UserNotFoundException.class, () -> userRepository.findUsers(names));
    }


}