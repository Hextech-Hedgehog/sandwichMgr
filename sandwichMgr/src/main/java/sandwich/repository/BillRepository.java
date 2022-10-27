package sandwich.repository;

import sandwich.model.Bill;
import sandwich.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BillRepository {

    void addBill(Bill bill);
    void addBills(List<Bill> bills);
    Bill findBillByMonth(LocalDate date);
    void removeBill(Bill bill);
    void removeBills(Set<Bill> bills);
    List<Bill> getAllBills();

}
