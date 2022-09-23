package model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class Bill {

    HashMap<LocalDate, Order> orders;

    public Bill(Set<Order> orders) {
        this.orders = orders.stream().collect(Collectors.toMap(Order::getDate, o -> o, (k, v) -> v, HashMap::new));
    }

    public void addOrder(Order order) {
        this.orders.put(order.getDate(), order);
    }

    public void addOrders(Order[] orders) {
        this.orders.putAll(Arrays.stream(orders).collect(Collectors.toMap(Order::getDate, (o) -> o)));
    }
}
