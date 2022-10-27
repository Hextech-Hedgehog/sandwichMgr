package sandwich.repository;

import sandwich.model.Sandwich;

import java.util.List;
import java.util.Map;

public interface SandwichRepository {

    void addSandwich(Sandwich sandwich, int amount);
    void addSandwiches(Map<Sandwich, Integer> sandwiches);
    Sandwich findSandwich(String sandwichName);
    Map<Sandwich, Integer> findSandwiches(List<String> sandwichNames);
    void removeSandwich(Sandwich sandwich, int amount);
    void removeSandwiches(Map<Sandwich, Integer> sandwiches);
    Map<Sandwich, Integer> getAllSandwiches();

}
