package sandwich.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name="bill")
public class Bill {

    @Id
    @SequenceGenerator(name="billGen", sequenceName = "bill_bid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billGen")
    @Column(name="bid")
    private int billId;
    @JsonIgnore
    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = {MERGE, PERSIST})
    @JoinColumn(name="o_bid")
    private List<Order> orders = new ArrayList<>();
    @Column(name="bdate")
    private LocalDate billDate;

    public Bill() {
        LocalDate now = LocalDate.now();
        this.billDate = LocalDate.of(now.getYear(), now.getMonth(), 1);
    }

    public Bill(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        this.billDate = date.minusDays(dayOfMonth - 1);
    }

    public Bill(LocalDate billDate, List<Order> orders) {
        this(billDate);
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (!orders.contains(order))
            this.orders.add(order);
    }

    public void addOrders(List<Order> orders) {
        this.orders.addAll(orders);
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
