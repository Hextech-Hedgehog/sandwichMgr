package sandwich.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.exception.SandwichNotFoundException;
import sandwich.model.Shop;

import static org.junit.jupiter.api.Assertions.*;

/*@SpringBootTest
class SandwichTypeFileRepoTest {

    SandwichTypeRepo repo;

    @BeforeEach
    public void setUp() {
        repo = SandwichTypeFileRepo.getInstance();
    }

    @AfterEach
    public void  tearDown() {
        repo = null;
    }

    @Test
    public void testThrowsSandwichNogFoundException() {
        assertThrows(SandwichNotFoundException.class, ()-> repo.getSandwich(Shop.VLEUGELS, null));
    }

}*/