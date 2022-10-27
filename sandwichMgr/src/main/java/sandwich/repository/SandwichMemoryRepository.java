package sandwich.repository;

import org.springframework.stereotype.Repository;
import sandwich.model.Sandwich;
import sandwich.model.SandwichType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SandwichMemoryRepository implements SandwichRepository {

    Map<Sandwich, Integer> sandwiches = new HashMap<>();

    public SandwichMemoryRepository () {
        List<SandwichType> sandwichTypes = new ArrayList<SandwichType>(){{
            add(new SandwichType("meat ball", "bread", "beef", "mayonnaise"));
            add(new SandwichType("martino", "bread", "beef", "eggs", "onions", "tomatoes", "pickles", "martino sauce"));
            add(new SandwichType("ham", "bread", "salad", "ham", "mayonnaise"));
            add(new SandwichType("salami", "bread", "salami", "mayonnaise"));
            add(new SandwichType("roast beef", "bread", "roasted beef", "mayonnaise"));
        }};
        Map<Sandwich, Integer> sandwiches = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(sandwichTypes.get(0), true, false), 5);
            put(new Sandwich(sandwichTypes.get(1), false, false), 2);
            put(new Sandwich(sandwichTypes.get(2), true, true), 1);
            put(new Sandwich(sandwichTypes.get(3), false, false), 4);
            put(new Sandwich(sandwichTypes.get(2), true, false), 2);
            put(new Sandwich(sandwichTypes.get(4), true, false), 1);
            put(new Sandwich(sandwichTypes.get(1), false, false), 1);
        }};
        this.addSandwiches(sandwiches);
    }

    @Override
    public void addSandwich(Sandwich sandwich, int amount) {
        if (this.sandwiches.containsKey(sandwich))
            this.sandwiches.put(sandwich, this.sandwiches.get(sandwich) + amount);
        else
            this.sandwiches.put(sandwich, amount);
    }

    @Override
    public void addSandwiches(Map<Sandwich, Integer> sandwiches) {
        sandwiches.forEach((k, v) -> this.sandwiches.merge(k, v, (oldV, newV) -> oldV + newV));
    }

    @Override
    public Sandwich findSandwich(String sandwichName) {
        return this.sandwiches.keySet().stream().filter(sand -> sand.getSandwichType().getSandwichName().equalsIgnoreCase(sandwichName)).findFirst().orElse(null);
    }

    @Override
    public Map<Sandwich, Integer> findSandwiches(List<String> sandwichNames) {
        return this.sandwiches.entrySet().stream().filter(sand -> sandwichNames.contains(sand.getKey().getSandwichType().getSandwichName())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void removeSandwich(Sandwich sandwich, int amount) {
        if (sandwiches.containsKey(sandwich)) {
            if (sandwiches.get(sandwich) - amount >= 1)
                sandwiches.put(sandwich, sandwiches.get(sandwich) - amount);
            else
                sandwiches.remove(sandwich);
        }
    }

    @Override
    public void removeSandwiches(Map<Sandwich, Integer> sandwiches) {
        sandwiches.entrySet().stream().filter(sandwich -> this.sandwiches.containsKey(sandwich.getKey())).forEach(sandwich -> this.removeSandwich(sandwich.getKey(), sandwich.getValue()));
    }

    @Override
    public Map<Sandwich, Integer> getAllSandwiches() {
        return this.sandwiches;
    }

}
