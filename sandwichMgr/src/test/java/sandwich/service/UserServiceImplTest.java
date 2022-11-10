package sandwich.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.SpringSandwichApplication;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.UserNotFoundException;
import sandwich.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringSandwichApplication.class)
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void findUserAlbusTest() throws UserNotFoundException {
        assertEquals("Albus", userService.findUser("Albus").getFirstName());
    }

    @Test
    public void findUserHarryTest() throws UserNotFoundException {
        assertEquals("Harry", userService.findUser("Harry").getFirstName());
    }

    @Test
    public void findUserArchibaldTest() throws UserNotFoundException {
        assertEquals("Archibald", userService.findUser("Archibald").getFirstName());
    }

    @Test
    public void findUserThrowsUSerNotFoundExceptionTest() {
        assertThrows(UserNotFoundException.class, () -> userService.findUser(null));
    }

    @Test
    public void findUsersByNamesWith3NamesInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Bob"); names.add("Peter"); names.add("Mary");
        assertEquals(names, userService.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()()));
    }

    @Test
    public void findUsersByNamesWith2NamesInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Gwen"); names.add("Charlie");
        assertEquals(names, userService.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findUsersByNamesWith1NameInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Archibald");
        assertEquals(names, userService.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findUsersByNamesWithEmptyArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        assertEquals(names, userService.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findUsersWithOneFakeNameInArrayTest() {
        List<String> names = new ArrayList<>();
        names.add("Bob"); names.add("Albus"); names.add("Mastodon");
        assertThrows(UserNotFoundException.class, () -> userService.findUsers(names));
    }

    @Test
    public void findUsersWithNullNameInArrayTest() {
        List<String> names = new ArrayList<>();;
        names.add("Bob"); names.add("Albus"); names.add(null);
        assertThrows(UserNotFoundException.class, () -> userService.findUsers(names));
    }





}