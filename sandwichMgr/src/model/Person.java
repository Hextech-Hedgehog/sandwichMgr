package model;

public abstract class Person {

private String firstName;
private boolean sandwichPayedByAbis;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public Sandwich orderSandwich(SandwichType sandwichType, boolean asClub, boolean withButter) {
        return orderSandwich(sandwichType, asClub, withButter, "");
    }
    public Sandwich orderSandwich(SandwichType sandwichType, boolean asClub, boolean withButter, String optionalRequirement) {
        return new Sandwich(sandwichType, asClub, withButter, optionalRequirement);
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
