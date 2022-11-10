package sandwich.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.SpringSandwichApplication;
import sandwich.exception.UserNotFoundException;
import sandwich.model.User;

import java.util.ArrayList;
import java.util.List;
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
        List<String> names = new ArrayList<>();
        names.add("Peter"); names.add("Mary"); names.add("Bob");
        assertEquals(names, userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }       // TODO finds all three but in different order

    @Test
    public void findUsersByNamesWith2NamesInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Charlie"); names.add("Gwen");
        assertEquals(names, userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }           // TODO finds all two but in different order

    @Test
    public void findUsersByNamesWith1NameInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Archibald");
        assertEquals(names, userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findUsersByNamesWithEmptyArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        assertEquals(names, userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findUsersWithOneFakeNameInArrayTest() {
        List<String> names = new ArrayList<>();
        names.add("Bob"); names.add("Albus"); names.add("Mastodon");
        assertThrows(UserNotFoundException.class, () -> userRepository.findUsers(names));
    }

    @Test
    public void findUsersWithNullNameInArrayTest() {
        List<String> names = new ArrayList<>();
        names.add("Bob"); names.add("Albus"); names.add(null);
        assertThrows(UserNotFoundException.class, () -> userRepository.findUsers(names));
    }


}