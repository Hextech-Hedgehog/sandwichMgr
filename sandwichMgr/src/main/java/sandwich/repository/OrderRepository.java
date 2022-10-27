package sandwich.repository;

import sandwich.model.Order;
import java.time.LocalDate;
import java.util.Set;

public interface OrderRepository {
    void addOrder(Order order);
    void addOrders(Set<Order> orders);
    Order findOrder(LocalDate date);
    Set<Order> findOrders(Set<LocalDate> dates);
    void removeOrder(Order order);
    void removeOrders(Set<Order> orders);
    Set<Order> getAllOrders();
}
