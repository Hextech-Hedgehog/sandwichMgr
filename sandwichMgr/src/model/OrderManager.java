package model;

import java.util.List;

public class OrderManager extends Person {

    private List<Order> orders;

    public OrderManager(String firstName) {
        super(firstName);
        this.setSandwichPayedByAbis(false);
    }

    public Sandwich[] viewOrders() {
        return null;
    }

    public void printOrders(List<Order> orders) {
        for (Order order: orders) {
            System.out.println(order);
        }

    }



    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
