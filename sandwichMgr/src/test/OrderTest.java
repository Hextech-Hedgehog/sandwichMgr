package test;

import model.Order;
import model.Sandwich;
import model.SandwichType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void addSandwich() {
        Sandwich sandwich = new Sandwich(SandwichType.MEAT_BALL, true, false);
        order.addSandwich(sandwich);
        assertEquals(true, order.hasSandwich(sandwich));
    }

    @Test
    void addSandwiches() {
        Map<Sandwich, Integer> sandwiches3 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.SALAMI, false, false), 4);
            put(new Sandwich(SandwichType.HAM, true, false), 3);
            put(new Sandwich(SandwichType.MEAT_BALL, false, false), 2);
        }};
        Map<Sandwich, Integer> sandwiches2 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.SALAMI, false, true), 5);
            put(new Sandwich(SandwichType.HAM, true, false), 2);
        }};
        Map<Sandwich, Integer> sandwiches4 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.SALAMI, false, true), 5);
            put(new Sandwich(SandwichType.SALAMI, false, false), 4);
            put(new Sandwich(SandwichType.HAM, true, false), 5);
            put(new Sandwich(SandwichType.MEAT_BALL, false, false), 2);
        }};
        order.addSandwiches(sandwiches2);
        order.addSandwiches(sandwiches3);
        assertEquals(sandwiches4, order.getSandwiches());
    }
}