package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sandwich.SpringSandwichApplication;
import sandwich.model.Sandwich;
import sandwich.model.SandwichType;
import sandwich.repository.SandwichRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = SpringSandwichApplication.class)
class SandwichRepositoryTest {

    @Autowired
    SandwichRepository sandwichRepository;

    @MockBean
    Sandwich sandwich;

    @Test
    void addSandwich() {
        Sandwich sandwich1 = new Sandwich(new SandwichType("Gouda", "cheese"));
        assertNull(sandwichRepository.findSandwich(sandwich1.getSandwichType().getSandwichName()));
        sandwichRepository.addSandwich(sandwich1, 1);
        assertEquals(sandwich1, sandwichRepository.findSandwich(sandwich1.getSandwichType().getSandwichName()));
    }

    @Test
    void addSandwiches() {
    }

    @Test
    void findSandwich() {
    }

    @Test
    void findSandwiches() {
    }

    @Test
    void removeSandwich() {
    }

    @Test
    void removeSandwiches() {
    }

    @Test
    void getAllSandwiches() {
    }
}