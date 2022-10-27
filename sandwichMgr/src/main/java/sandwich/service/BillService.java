package sandwich.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.NonAuthorizedPersonnelException;
import sandwich.model.*;

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
    Sandwich orderSandwich(Shop shop, Person person) throws NonAuthorizedPersonnelException;

}
