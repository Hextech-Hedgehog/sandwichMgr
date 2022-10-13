package repository;

import exception.SandwichNotFoundException;
import model.SandwichType;
import model.Shop;

import java.util.List;
import java.util.Set;

public interface SandwichTypeRepo {

    void writeSandwichesToFile(List<SandwichType> sandwichTypes, Shop shop);
    Set<SandwichType> getSandwiches(Shop shop);
    SandwichType getSandwich(Shop shop, String sandwichName) throws SandwichNotFoundException;

}
