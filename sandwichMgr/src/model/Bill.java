package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bill {

    Map<LocalDate, Set<Order>> orders;

    public Bill(Set<Order> orders) {
        this.orders = new HashMap<>();
        this.addOrders(orders);
    }

    public void addOrder(Order order) {
        Set<Order> orders = this.orders.get(order.getDate());
        if (orders == null) {
            orders = new HashSet<>();
            orders.add(order);
            this.orders.put(order.getDate(), orders);
        } else
            orders.add(order);
    }

    public void addOrders(Set<Order> orders) {
        orders.stream().forEach(o -> addOrder(o));
    }

    public void viewOrderByDate(LocalDate date) {
        this.orders.get(date).forEach(Order::printOrderInfo);
    }

    public Set<Order> getOrdersByDate(LocalDate date) {
        return this.orders.get(date);
    }
}
