package sandwich.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.SpringSandwichApplication;
import sandwich.exception.SessionNotFoundException;
import sandwich.exception.UserNotFoundException;
import sandwich.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringSandwichApplication.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Mock
    private User user;
    @Spy
    private ArrayList<User> users;

    @Mock private Course course;
    @Mock private Session session;
    @Spy  private ArrayList<Session> sessions;
    @Mock private Order order;
    @Mock private Sandwich sandwich;


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
        assertThrows(IllegalArgumentException.class, () -> userService.findUser(null));
    }

    @Test
    public void findUsersByNamesWith3NamesInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Bob"); names.add("Peter"); names.add("Mary");
        assertTrue(userService.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()).containsAll(names));
    }

    @Test
    public void findUsersByNamesWith2NamesInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Gwen"); names.add("Charlie");
        assertTrue(userService.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()).containsAll(names));
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
        assertThrows(IllegalArgumentException.class, () -> userService.findUsers(names));
    }



    @Test
    public void addUserTest() {
        int size = userService.getAllUsers().size();
        userService.addUser(user);
        assertEquals(size +1, userService.getAllUsers().size());
    }

    @Test void addMultipleUsersTest() {
        for (int i = 0; i < 4; i++) {
            users.add(user);
        }
        int size = userService.getAllUsers().size();
        userService.addUsers(users);
        assertEquals(size +4, userService.getAllUsers().size());
    }

    @Test
    void  removeUserTest() {
        userService.addUser(user);
        int size = userService.getAllUsers().size();
        userService.removeUser(user);
        assertEquals(size -1, userService.getAllUsers().size());
    }

    @Test
    void removeMultipleUsers() {
        for (int i = 0; i < 8; i++) {
            users.add(user);
        }
        userService.addUsers(users);
        int size = userService.getAllUsers().size();
        userService.removeUsers(users);
        assertEquals(size -8, userService.getAllUsers().size());
    }

    /*
    @Test
    void getOrderTest() throws SessionNotFoundException {
        order.addSandwich(sandwich);                     TODO refactor classes to make this test workable
        ...
        session.setStartDate(LocalDate.now()); session.setEndDate(LocalDate.now());
        sessions.add(session);
        course.setSessions(sessions);
        course.addParticipant(user);
        user.getCourse().getSessionByDate(LocalDate.now()).getDailyOrder();
    }
    */






}