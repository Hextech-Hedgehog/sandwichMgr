package repository;

import model.SandwichType;
import model.Shop;

import java.util.List;
import java.util.Set;

public interface SandwichTypeRepo {

    void writeToRepo(List<SandwichType> sandwichTypes, Shop shop);
    Set<SandwichType> readFromRepo(Shop shop);

}
