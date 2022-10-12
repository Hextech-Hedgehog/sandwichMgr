package model;

import java.time.LocalDate;
import java.util.List;

public class Admin extends Person {

    public Admin(String firstName) {
        super(firstName);
        this.setSandwichPayedByAbis(false);
    }

    public List<Sandwich> viewTotalOrdersInEternity() {
        // call upon
        return null;
    }

    public List<Sandwich> viewOrdersByDate(LocalDate date) {
        // call upon method in Bill Class / Order Repo
        return null;
    }

    public List<Sandwich> viewOrdersFromDateToDate(LocalDate startDate, LocalDate endDate) {
        // call upon method in Bill Class / Order Repo
        return null;
    }

    public void printOrders(List<Order> orders) {
        for (Order order: orders) {
            order.printOrderInfo();
        }
    }

    @Override
    public void makeOrder() {}
}
