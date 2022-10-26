package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BillTest {

    Bill b;

    @BeforeEach
    void setup() {
        Map<Sandwich, Integer> sandwiches = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "meat ball"), true, false), 5);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "martino"), false, false), 2);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "ham"), true, true), 1);
        }};

        Map<Sandwich, Integer> sandwiches2 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "salami"), false, false), 4);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "ham"), true, false), 2);
        }};

        Map<Sandwich, Integer> sandwiches3 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "roast beef"), true, false), 1);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "martino"), false, false), 1);
        }};
        Set<Order> orders = new HashSet<Order>(){{
            add(new Order(sandwiches));
            add(new Order(sandwiches2));
            add(new Order(sandwiches3));
        }};
        b = new Bill(orders);
    }

    @Test
    void addOrder() {
        Order order = new Order(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "roast beef"), true, false));
        b.addOrder(order);
        assertEquals(true, b.getOrdersByDate(LocalDate.now()).contains(order));
    }

    @Test
    void getOrdersByDate() {
        assertEquals(3, b.getOrdersByDate(LocalDate.now()).size());
    }
}