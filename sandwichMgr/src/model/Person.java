package model;

import factory.SandwichFactory;

import java.util.Scanner;

public abstract class Person {

    private String firstName;
    private boolean isSandwichPayedByAbis;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public Sandwich orderSandwich(Shop shop) {
        System.out.println("Please type in the sandwich of your choice ");
        Sandwich sandwich = selectSandwich(shop);
        System.out.println("Here is your sandwich: ");
        sandwich.printContents();
        System.out.println("Have a nice meal!");
        return sandwich;
    }

    private static Sandwich selectSandwich(Shop shop) {
        Scanner sc = new Scanner(System.in);
        Sandwich sandwich;

        while(true) {
            String sandwichName = sc.nextLine();
            SandwichType sd = SandwichType.getSandwichByName(shop, sandwichName);

            if (sd != null) {
                boolean asClub = selectExtra("Would you like your sandwich as a club ?");
                boolean withButter = selectExtra("Do you want butter on your sandwich ?");
                sandwich = SandwichFactory.getSandwichFactory().makeSandwich(sd).asClub(asClub).withButter(withButter).toSandwich();
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
