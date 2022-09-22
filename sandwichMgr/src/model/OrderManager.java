package model;

import java.util.List;

public class OrderManager extends Person {

    private List<Order> orders;

    public OrderManager(String firstName) {
        super(firstName);
    }

    public Sandwich[] viewOrders() {

    }



    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
