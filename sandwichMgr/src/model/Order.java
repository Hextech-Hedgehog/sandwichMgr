package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private Map<Sandwich, Integer> sandwiches = new HashMap<Sandwich, Integer>();
    private LocalDate date = LocalDate.now();

    public Map<Sandwich, Integer> getSandwiches() {
        return sandwiches;
    }

    public LocalDate getDate() {
        return date;
    }

    public Order(Sandwich sandwich) {
        this.sandwiches.put(sandwich, 1);
    }

    public Order(Map<Sandwich, Integer> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public void addSandwich(Sandwich sandwich) {
        if (this.sandwiches.containsKey(sandwich)) {
            int count = this.sandwiches.get(sandwich);
            this.sandwiches.put(sandwich, ++count);
        } else
            this.sandwiches.put(sandwich, 1);
    }

    public void addSandwiches(HashMap<Sandwich, Integer> sandwiches) {
        this.sandwiches.forEach((k, v) -> sandwiches.merge(k, v, (oldV, newV) -> oldV + newV));
    }

    public void printOrderInfo() {
        System.out.println(this.getClass().getSimpleName() + " " + this.getDate() + ": ");
        this.sandwiches.forEach((k, v) -> {
            System.out.print(v + " people ordered: ");
            k.printContents();
        });
        System.out.println();
    }
}
