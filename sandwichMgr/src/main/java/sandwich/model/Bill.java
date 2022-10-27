package sandwich.model;

import org.apache.logging.log4j.LogManager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bill {

    private Map<LocalDate, Set<Order>> orders;
    private LocalDate billDate;

    public Bill(Set<Order> orders) {
        this.orders = new HashMap<>();
        this.addOrders(orders);
        LocalDate now = LocalDate.now();
        this.billDate = LocalDate.of(now.getYear(), now.getMonth(), 1);
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
        Set<Order> orders = this.orders.get(date);
        if (orders == null)
            LogManager.getLogger().error("No order found at date " + date);
        else
            orders.forEach(Order::printOrderInfo);
    }

    public Set<Order> getOrdersByDate(LocalDate date) {
        return this.orders.get(date);
    }

    public Map<LocalDate, Set<Order>> getOrders() {
        return orders;
    }

    public void setOrders(Map<LocalDate, Set<Order>> orders) {
        this.orders = orders;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }
}
