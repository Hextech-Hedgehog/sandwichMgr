package sandwich.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
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
    private UserRepository userRepository;

    @Mock
    private User user;
    @Spy
    private ArrayList<User> users;


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
    public void findUserNullArgumentThrowsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> userRepository.findUser(null));
    }

    @Test
    public void findUsersByNamesWith3NamesInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Peter"); names.add("Mary"); names.add("Bob");
        assertTrue(userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()).containsAll(names));
    }

    @Test
    public void findUsersByNamesWith2NamesInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Charlie"); names.add("Gwen");
        assertTrue(userRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()).containsAll(names));;
    }

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
        assertThrows(IllegalArgumentException.class, () -> userRepository.findUsers(names));
    }

    @Test
    void addUserTest() {
        int size = userRepository.getAllUsers().size();
        userRepository.addUser(user);
        assertEquals(size +1, userRepository.getAllUsers().size());
    }

    @Test
    void addNullUserTest() {
        assertThrows(IllegalArgumentException.class, ()-> userRepository.addUser(null));
    }

    @Test
    void addMultipleUsersTest() {
        for (int i = 0; i < 5; i++) {
            users.add(user);
        }
        int size = userRepository.getAllUsers().size();
        userRepository.addUsers(users);
        assertEquals(size +5, userRepository.getAllUsers().size());
    }

    @Test
    void addMultipleUserWithOneNullObjectTest() {
        for (int i = 0; i < 5; i++) {
            users.add(user);
        }
        users.add(null);
        assertThrows(IllegalArgumentException.class, ()-> userRepository.addUsers(users));
    }

    @Test
    void  removeUserTest() {
        userRepository.addUser(user);
        int size = userRepository.getAllUsers().size();
        userRepository.removeUser(user);
        assertEquals(size -1, userRepository.getAllUsers().size());
    }

    @Test
    void removeNullUserTest() {
        assertThrows(IllegalArgumentException.class, ()-> userRepository.removeUser(null));
    }



    @Test
    void removeMultipleUsers() {
        for (int i = 0; i < 7; i++) {
            users.add(user);
        }
        userRepository.addUsers(users);
        int size = userRepository.getAllUsers().size();
        userRepository.removeUsers(users);
        assertEquals(size -7, userRepository.getAllUsers().size());
    }




}