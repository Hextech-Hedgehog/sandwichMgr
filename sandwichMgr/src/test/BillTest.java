package test;

import model.Bill;
import model.Order;
import model.Sandwich;
import model.SandwichType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    Bill b;

    @BeforeEach
    void setup() {
        Map<Sandwich, Integer> sandwiches = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.MEAT_BALL, true, false), 5);
            put(new Sandwich(SandwichType.MARTINO, false, false), 2);
            put(new Sandwich(SandwichType.HAM, true, true), 1);
        }};

        Map<Sandwich, Integer> sandwiches2 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.SALAMI, false, false), 4);
            put(new Sandwich(SandwichType.HAM, true, false), 2);
        }};

        Map<Sandwich, Integer> sandwiches3 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.ROAST_BEEF, true, false), 1);
            put(new Sandwich(SandwichType.MARTINO, false, false), 1);
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
        Order order = new Order(new Sandwich(SandwichType.ROAST_BEEF, true, false));
        b.addOrder(order);
        assertEquals(true, b.getOrdersByDate(LocalDate.now()).contains(order));
    }

    @Test
    void getOrdersByDate() {
        assertEquals(3, b.getOrdersByDate(LocalDate.now()).size());
    }
}