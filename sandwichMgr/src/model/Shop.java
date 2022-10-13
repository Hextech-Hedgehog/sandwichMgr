package model;

import repository.SandwichTypeFileRepo;
import repository.SandwichTypeRepo;

import java.util.HashSet;
import java.util.Set;

public enum Shop {
    PINKYS("sandwichTypePinkys.txt"),
    VLEUGELS("sandwichTypeVleugels.txt");

    private final String pathWay;

    Shop(String pathWay) {
        this.pathWay = pathWay;
    }


    public String getPathWay() {
        return pathWay;
    }
}


