package sandwich.repository;

import sandwich.exception.SandwichNotFoundException;
import sandwich.model.SandwichType;
import sandwich.model.Shop;

import java.util.List;
import java.util.Set;

public interface SandwichTypeRepository {

    void addSandwichType(Shop shop, SandwichType sandwichType);
    void addSandwichTypes(Shop shop, List<SandwichType> sandwichTypes);
    SandwichType getSandwich(Shop shop, String sandwichName) throws SandwichNotFoundException;
    Set<SandwichType> getSandwiches(Shop shop);
    void removeSandwichType(Shop shop, SandwichType sandwich);

}
