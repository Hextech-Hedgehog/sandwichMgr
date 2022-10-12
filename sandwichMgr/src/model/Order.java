package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private Map<Sandwich, Integer> sandwiches = new HashMap<Sandwich, Integer>();
    private LocalDate date = LocalDate.now();

    public Order () {}

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

    public void addSandwiches(Map<Sandwich, Integer> sandwiches) {
        sandwiches.forEach((k, v) -> this.sandwiches.merge(k, v, (oldV, newV) -> oldV + newV));
    }

    public void printOrderInfo() {
        System.out.println(this.getClass().getSimpleName() + " " + this.getDate() + ": ");
        this.sandwiches.forEach((k, v) -> {
            System.out.print(v + " people ordered: ");
            k.printContents();
        });
        System.out.println();
    }

    public Map<Sandwich, Integer> getSandwiches() {
        return sandwiches;
    }

    public boolean hasSandwich(Sandwich sandwich) {
        return this.sandwiches.containsKey(sandwich);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Order) {
            Order order = (Order)o;
            boolean isMapOfSandwichEqual = this.sandwiches.size() == order.sandwiches.size() && this.sandwiches.entrySet().stream().allMatch(elem -> elem.getValue() == order.sandwiches.get(elem.getKey()));
            return this.date.equals(order.date) && isMapOfSandwichEqual;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hasCode = 13 + this.sandwiches.hashCode();
        hasCode += 11 * this.date.hashCode();
        return hasCode;
    }
}
