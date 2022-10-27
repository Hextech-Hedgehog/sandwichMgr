package sandwich.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.exception.ParticipantNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/*@SpringBootTest
class CourseTest {

    Course course;

    @BeforeEach
    public void setUp() {
        course = new Course("java");
    }

    @AfterEach
    public void tearDown() {
        course = null;
    }

    @Test
    public void testThrowsParticipantNotFoundException() throws ParticipantNotFoundException {
        assertThrows(ParticipantNotFoundException.class, () -> course.getCourseParticipantByName(null));
    }

}*/