package sandwich.service;

import org.springframework.stereotype.Service;
import sandwich.model.*;
<<<<<<< HEAD
import sandwich.repository.BillJpaRepository;
=======
>>>>>>> 0bb6aae4f84f17213497ebb1a1e18e3d6d05e413

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public interface BillService {

    void addBill(Bill bill);
    void addBills(List<Bill> bills);
    Bill findBillByMonth(LocalDate date);
    void removeBill(Bill bill);
    void removeBills(Set<Bill> bills);
    List<Bill> getAllBills();
    Bill getThisMonthBill();
    Sandwich orderSandwich(String shopName);
    Sandwich orderSandwich(String shopName, Sandwich sandwich);
    void setBillRepository(BillJpaRepository billRepository);
    List<Order> findOrdersByBillAndDate(int billId, LocalDate date);
    List<Order> findOrdersByDate(LocalDate date);
    void updateBill(Bill bill);

}
