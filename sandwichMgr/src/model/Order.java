package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches = new ArrayList<>();
    private LocalDate date = LocalDate.now();

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public LocalDate getDate() {
        return date;
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
