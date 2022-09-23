package model;

public abstract class Person {

private String firstName;
private boolean sandwichPayedByAbis;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public Order orderSandwich(SandwichType sandwichType, boolean asClub, boolean withButter) {
        return orderSandwich(sandwichType, asClub, withButter, "");                 // save Order to OrderRepo
    }
    public Order orderSandwich(SandwichType sandwichType, boolean asClub, boolean withButter, String optionalRequirement) {
        return new Order(new Sandwich(sandwichType, asClub, withButter, optionalRequirement));      // save Order to OrderRepo
                                                                                // add order to Bill if paid by Abis
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isSandwichPayedByAbis() {
        return sandwichPayedByAbis;
    }

    public void setSandwichPayedByAbis(boolean sandwichPayedByAbis) {
        this.sandwichPayedByAbis = sandwichPayedByAbis;
    }
}
