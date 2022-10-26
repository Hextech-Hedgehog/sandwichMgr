package sandwich.repository;

import sandwich.exception.SandwichNotFoundException;
import sandwich.model.SandwichType;
import sandwich.model.Shop;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface SandwichTypeRepo {

    void writeSandwichesToFile(List<SandwichType> sandwichTypes, Shop shop) throws IOException;
    Set<SandwichType> getSandwiches(Shop shop);
    SandwichType getSandwich(Shop shop, String sandwichName) throws SandwichNotFoundException;

}
