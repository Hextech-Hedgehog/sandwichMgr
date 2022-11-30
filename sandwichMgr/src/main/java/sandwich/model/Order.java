package sandwich.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @SequenceGenerator(name="billGen", sequenceName = "bill_bid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billGen")
    @Column(name="orid")
    private int orderId;
    @OneToMany(targetEntity = Sandwich.class, cascade = {MERGE, PERSIST})
    @JoinColumn(name="s_orid")
    private List<Sandwich> sandwiches = new ArrayList<>();
    @Column(name="odate")
    private LocalDate date;

    public Order () {
        this.date = LocalDate.now();
    }

    public Order(List<Sandwich> sandwiches, LocalDate date) {
        this.sandwiches = sandwiches;
        this.date = date;
    }

    public void addSandwich(Sandwich sandwich) {
        this.sandwiches.add(sandwich);
    }

    public void addSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches.addAll(sandwiches);
    }

    public void printOrderInfo() {
        System.out.println(this.getClass().getSimpleName() + " " + this.getDate() + ": ");

        getSandwiches().forEach((k, v) -> {
            System.out.print(v + " people ordered: ");
            v.get(0).getSandwichType().printContents();
        });
        System.out.println();
    }

    public Map<String, List<Sandwich>> getSandwiches() {
        Map<String, List<Sandwich>> duplicateSandwiches = new HashMap<>();
        this.sandwiches.stream().forEach(s -> {
            duplicateSandwiches.computeIfAbsent(s.getSandwichType().getSandwichName(), v -> new ArrayList<>()).add(s);
        });
        return duplicateSandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Order) {
            Order order = (Order)o;
            Map<String, List<Sandwich>> mySandwiches = getSandwiches();
            Map<String, List<Sandwich>> theirSandwiches = order.getSandwiches();
            boolean isMapOfSandwichEqual = mySandwiches.size() == theirSandwiches.size() && mySandwiches.entrySet().stream().allMatch(elem -> (theirSandwiches.get(elem.getKey()) != null) && (elem.getValue().size() == theirSandwiches.get(elem.getKey()).size()));
            return this.date.equals(order.date) && isMapOfSandwichEqual;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hasCode = 13 + this.sandwiches.hashCode();
        hasCode += 11 * this.date.hashCode();
        return hasCode;
    }
}
