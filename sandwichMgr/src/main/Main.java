package main;

import model.Bill;
import model.Order;
import model.Sandwich;
import model.SandwichType;

import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Map<Sandwich, Integer> sandwiches = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.MEAT_BALL, true, false), 5);
            put(new Sandwich(SandwichType.MARTINO, false, false), 2);
            put(new Sandwich(SandwichType.HAM, true, true), 1);
        }};

        Map<Sandwich, Integer> sandwiches2 = new HashMap<>(){{
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
        Bill b = new Bill(orders);


        /* print every orders */
        // orders.forEach(Order::printOrderInfo);


        /* test if two instances of sandwiches of same type are considered equals */
        /*Order test = new Order(sandwiches);
        test.printOrderInfo();
        test.addSandwich(new Sandwich(SandwichType.MARTINO, false, false));
        test.addSandwich(new Sandwich(SandwichType.MARTINO, true, false));
        test.printOrderInfo();*/

        /* test bill and print every order of a bill by specific date */
        b.viewOrderByDate(LocalDate.now());
    }

}
