package sandwich.repository;

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
class UserMemoryRepositoryTest {

    @Autowired
    UserRepository personRepository;

    @Test
    public void findPersonAlbusTest() throws UserNotFoundException {
        assertEquals("Albus", personRepository.findUser("Albus").getFirstName());
    }

    @Test
    public void findPersonHarryTest() throws UserNotFoundException {
        assertEquals("Harry", personRepository.findUser("Harry").getFirstName());
    }

    @Test
    public void findPersonArchibaldTest() throws UserNotFoundException {
        assertEquals("Archibald", personRepository.findUser("Archibald").getFirstName());
    }

    @Test
    public void findCourseThrowsCourseNotFoundExceptionTest() throws UserNotFoundException {
        assertThrows(CourseNotFoundException.class, () -> personRepository.findUser(null));
    }

    @Test
    public void findPeopleByNamesWith3NamesInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Bob"); names.add("Peter"); names.add("Mary");
        assertEquals(names, personRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleByNamesWith2NamesInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Gwen"); names.add("Charlie");
        assertEquals(names, personRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleByNamesWith1NameInArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Archibald");
        assertEquals(names, personRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleByNamesWithEmptyArrayTest() throws UserNotFoundException {
        List<String> names = new ArrayList<>(); // TODO check if this produces a NullPointerException or not?
        assertEquals(names, personRepository.findUsers(names).stream().map(User::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleWithOneFakeNameInArrayTest() {
        List<String> names = new ArrayList<>();
        names.add("Bob"); names.add("Albus"); names.add(null);
        assertThrows(UserNotFoundException.class, () -> personRepository.findUsers(names));
    }


}