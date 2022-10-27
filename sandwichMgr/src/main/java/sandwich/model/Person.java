package sandwich.model;

import sandwich.exception.SandwichNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.factory.SandwichFactory;

import java.io.IOException;
import java.util.Scanner;

public abstract class Person {

    private String firstName;
    private boolean isSandwichPayedByAbis;

    public Person(String firstName) {
        this.firstName = firstName;
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
