package sandwich.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.SpringSandwichApplication;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.PersonNotFoundException;
import sandwich.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringSandwichApplication.class)
class PersonServiceImplTest {

    @Autowired
    PersonService personService;

    @Test
    public void findPersonAlbusTest() throws PersonNotFoundException {
        assertEquals("Albus", personService.findPerson("Albus").getFirstName());
    }

    @Test
    public void findPersonHarryTest() throws PersonNotFoundException {
        assertEquals("Harry", personService.findPerson("Harry").getFirstName());
    }

    @Test
    public void findPersonArchibaldTest() throws PersonNotFoundException {
        assertEquals("Archibald", personService.findPerson("Archibald").getFirstName());
    }

    @Test
    public void findCourseThrowsCourseNotFoundExceptionTest() throws PersonNotFoundException {
        assertThrows(CourseNotFoundException.class, () -> personService.findPerson(null));
    }

    @Test
    public void findPeopleByNamesWith3NamesInArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Bob"); names.add("Peter"); names.add("Mary");
        assertEquals(names, personService.findPeople(names).stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleByNamesWith2NamesInArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Gwen"); names.add("Charlie");
        assertEquals(names, personService.findPeople(names).stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleByNamesWith1NameInArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>();         // TODO might work better with a Set than a List
        names.add("Archibald");
        assertEquals(names, personService.findPeople(names).stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleByNamesWithEmptyArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>(); // TODO check if this produces a NullPointerException or not?
        assertEquals(names, personService.findPeople(names).stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void findPeopleWithOneFakeNameInArrayTest() throws PersonNotFoundException {
        List<String> names = new ArrayList<>();
        names.add("Bob"); names.add("Albus"); names.add(null);
        assertThrows(CourseNotFoundException.class, () -> personService.findPeople(names));
    }



}