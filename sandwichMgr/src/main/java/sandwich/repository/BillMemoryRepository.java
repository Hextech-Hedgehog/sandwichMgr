package sandwich.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sandwich.model.Bill;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class BillMemoryRepository implements BillRepository {

    private List<Bill> bills = new ArrayList<>();
    private OrderRepository or;

    public BillMemoryRepository(@Autowired OrderRepository orderRepository) {
        this.or = orderRepository;
    }

    @PostConstruct
    private void init() {
        List<Bill> bills = new ArrayList<Bill>(){{
            add(new Bill(or.getAllOrders().stream().limit(2).collect(Collectors.toSet())));
            add(new Bill(or.getAllOrders().stream().skip(2).collect(Collectors.toSet())));
        }};
        bills.get(0).setBillDate(bills.get(0).getOrders().keySet().stream().findFirst().get());
        bills.get(1).setBillDate(bills.get(1).getOrders().keySet().stream().findFirst().get());
        this.bills.addAll(bills);
    }

    @Override
    public void addBill(Bill bill) {
        this.bills.add(bill);
    }

    @Override
    public void addBills(List<Bill> bills) {
        this.bills.addAll(bills);
    }

    @Override
    public Bill findBillByMonth(LocalDate date) {
        return this.bills.stream().filter(b -> b.getBillDate().getYear() == date.getYear() && b.getBillDate().getMonth() == date.getMonth()).findFirst().orElse(null);
    }

    @Override
    public void removeBill(Bill bill) {
        this.bills.remove(bill);
    }

    @Override
    public void removeBills(Set<Bill> bills) {
        this.bills.removeAll(bills);
    }

    @Override
    public List<Bill> getAllBills() {
        return this.bills;
    }
}
