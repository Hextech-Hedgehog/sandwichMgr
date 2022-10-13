package test;

import model.Order;
import model.Sandwich;
import model.SandwichType;
import model.Shop;
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
        Sandwich sandwich = new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "meat ball"), true, false);
        order.addSandwich(sandwich);
        assertEquals(true, order.hasSandwich(sandwich));
    }

    @Test
    void addSandwiches() {
        Map<Sandwich, Integer> sandwiches2 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "salami"), false, false), 4);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "ham"), true, false), 2);
        }};
        Map<Sandwich, Integer> sandwiches3 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "salami"), false, false), 1);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "martino"), false, false), 1);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "meat ball"), false, true), 2);
        }};
        Map<Sandwich, Integer> sandwiches4 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "salami"), false, false), 5);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "ham"), true, false), 2);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "martino"), false, false), 1);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "meat ball"), false, true), 2);
        }};
        order.addSandwiches(sandwiches2);
        order.addSandwiches(sandwiches3);
        assertEquals(sandwiches4, order.getSandwiches());
    }
}