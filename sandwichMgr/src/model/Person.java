package model;

import exception.SandwichNotFoundException;
import factory.SandwichFactory;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;

public abstract class Person {

    private String firstName;
    private boolean isSandwichPayedByAbis;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public abstract void makeOrder();

    public Sandwich orderSandwich() {
        System.out.println("Please type in the sandwich of your choice ");
        Sandwich sandwich = selectSandwich();
        System.out.println("Here is your sandwich: ");
        sandwich.printContents();
        System.out.println("Have a nice meal!");
        return sandwich;
    }

    private static Sandwich selectSandwich() {
        Scanner sc = new Scanner(System.in);
        Sandwich sandwich;

        while(true) {
            String sandwichName = sc.nextLine();
            SandwichType sd = null;
            try {
                sd = SandwichType.getSandwichTypeByName(sandwichName);
            } catch (SandwichNotFoundException e) {
                LogManager.getLogger("error").error(e.getMessage());
            }

            if (sd != null) {
                boolean asClub = selectExtra("Would you like your sandwich as a club ?");
                boolean withButter = selectExtra("Do you want butter on your sandwich ?");
                sandwich = SandwichFactory.getSandwichFactory().makeSandwich(sandwichName).asClub(asClub).withButter(withButter).toSandwich();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isSandwichPayedByAbis() {
        return isSandwichPayedByAbis;
    }

    public void setSandwichPayedByAbis(boolean sandwichPayedByAbis) {
        this.isSandwichPayedByAbis = sandwichPayedByAbis;
    }
}
