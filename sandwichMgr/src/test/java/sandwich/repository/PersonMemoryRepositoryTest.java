package sandwich.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.SpringSandwichApplication;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringSandwichApplication.class)
class PersonMemoryRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void findPersonAlbusTest() throws PersonNotFoundException {
        assertEquals("Albus", personRepository.findUserByName("Albus").getFirstName());
    }

    @Test
    public void findPersonHarryTest() throws PersonNotFoundException {
        assertEquals("Harry", personRepository.findUserByName("Harry").getFirstName());
    }

    @Test
    public void findPersonArchibaldTest() throws PersonNotFoundException {
        assertEquals("Archibald", personRepository.findUserByName("Archibald").getFirstName());
    }

    @Test
    public void findCourseThrowsCourseNotFoundExceptionTest() throws PersonNotFoundException {
        assertThrows(CourseNotFoundException.class, () -> personRepository.findUser(null));
    }

    @Test
    public void findPeopleByNamesWith3NamesInArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Bob"); names.add("Peter"); names.add("Mary");
        //assertEquals(names, personRepository.findPeople(names).stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleByNamesWith2NamesInArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Gwen"); names.add("Charlie");
        //assertEquals(names, personRepository.findPeople(names).stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleByNamesWith1NameInArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Archibald");
        //assertEquals(names, personRepository.findPeople(names).stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleByNamesWithEmptyArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>(); // TODO check if this produces a NullPointerException or not?
        //assertEquals(names, personRepository.findPeople(names).stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleWithOneFakeNameInArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Bob"); names.add("Albus"); names.add(null);
        assertThrows(CourseNotFoundException.class, () -> personRepository.findPeople(names));
    }


}