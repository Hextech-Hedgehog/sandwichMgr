package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches = new ArrayList<>();

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public Order(Sandwich sandwich) {
        this.sandwiches.add(sandwich);
    }

    public Order(List<Sandwich> sandwiches) {
        this.sandwiches.addAll(sandwiches);
    }

    public void printOrderInfo() {
        this.sandwiches.forEach(Sandwich::printContents);
    }

}
