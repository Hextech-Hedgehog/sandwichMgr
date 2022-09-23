package main;

import model.Bill;
import model.Order;
import model.Sandwich;
import model.SandwichType;

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
        //b.viewOrderByDate(LocalDate.now());

        /* test sandwich order */
        makeSandwichOrder();
    }

    public static void makeSandwichOrder() {
        System.out.println("Welcome to the sandwich manager terminal!");
        System.out.println("We have a few sandwiches options on the menu for you today!");
        System.out.println("You can even add extras along with your desired sandwich!");
        System.out.println("===========================================================================");
        System.out.println("SANDWICHES: \n");
        SandwichType.printMenu();
        System.out.println("===========================================================================");
        System.out.println("EXTRAS: \n");
        System.out.println("As club: Salad, tomato, carrot or pickles, scrambled eggs, mayonnaise");
        System.out.println("Butter");
        System.out.println("===========================================================================\n");
        System.out.println("Please select your desired meal!");
        Sandwich sandwich = selectSandwich();
        System.out.println("Here is your sandwich: ");
        sandwich.printContents();
        System.out.println("Have a nice meal!");
    }

    private static Sandwich selectSandwich() {
        Scanner sc = new Scanner(System.in);
        Sandwich sandwich;

        while(true) {
            String sandwichName = sc.nextLine();
            SandwichType sd = SandwichType.getSandwichTypeByName(sandwichName);
            if (sd != null) {
                boolean asClub = selectExtra("Would you like your sandwich as a club ?");
                boolean withButter = selectExtra("Do you want butter on your sandwich ?");
                sandwich = new Sandwich(sd, asClub, withButter);
                break;
            }
            System.out.println("Please type in a correct sandwich option");
        }

        return sandwich;
    }

    private static boolean selectExtra(String extraDescription) {
        System.out.println(extraDescription);
        System.out.println("Type in y or n");
        Scanner sc = new Scanner(System.in);
        boolean extra;

        while(true) {
            String sandwichName = sc.nextLine();
            boolean result = sandwichName.equalsIgnoreCase("y");
            if (result || sandwichName.equalsIgnoreCase("n")) {
                extra = result;
                break;
            }
            System.out.println("Please type in Y or N");
        }

        return extra;
    }

}
