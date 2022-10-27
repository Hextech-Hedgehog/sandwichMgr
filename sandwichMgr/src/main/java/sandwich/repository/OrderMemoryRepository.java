package sandwich.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sandwich.model.Order;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderMemoryRepository implements OrderRepository {

    private Set<Order> orders = new HashSet<>();
    private SandwichRepository sr;

    public OrderMemoryRepository(@Autowired SandwichRepository sandwichRepository) {
        this.sr = sandwichRepository;
        List<String> sandwichNames = new ArrayList<String>(){{
            addAll(Arrays.asList("martino", "meat ball", "ham", "salami", "roast beef"));
        }};
        Set<Order> orders = new HashSet<Order>(){{
            add(new Order(sr.findSandwiches(sandwichNames.subList(0, 2)), LocalDate.of(2022, 10, 10)));
            add(new Order(sr.findSandwiches(sandwichNames.subList(2, 4)), LocalDate.of(2022, 10, 11)));
            add(new Order(sr.findSandwiches(sandwichNames.subList(4, 5)), LocalDate.of(2022, 10, 12)));
        }};
        this.addOrders(orders);
    }

    @Override
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public void addOrders(Set<Order> orders) {
        this.orders.addAll(orders);
    }

    @Override
    public Order findOrder(LocalDate date) {
        return this.orders.stream().filter(order -> order.getDate().isEqual(date)).findFirst().orElse(null);
    }

    @Override
    public Set<Order> findOrders(Set<LocalDate> dates) {
        return this.orders.stream().filter(order -> dates.contains(order.getDate())).collect(Collectors.toSet());
    }

    @Override
    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    @Override
    public void removeOrders(Set<Order> orders) {
        this.orders.removeAll(orders);
    }

    @Override
    public Set<Order> getAllOrders() {
        return this.orders;
    }
}
