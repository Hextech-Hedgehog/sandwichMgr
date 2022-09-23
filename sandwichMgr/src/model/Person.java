package model;

public abstract class Person {

private String firstName;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public Sandwich orderSandwich() {
        return new Sandwich();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
