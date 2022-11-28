package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.model.*;
import sandwich.utils.SandwichMaker;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Profile("production")
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public void addBill(Bill bill) {
        this.billRepository.addBill(bill);
    }

    @Override
    public void addBills(List<Bill> bills) {
        this.billRepository.addBills(bills);
    }

    @Override
    public Bill findBillByMonth(LocalDate date) {
        return this.billRepository.findBillByMonth(date);
    }

    @Override
    public void removeBill(Bill bill) {
        this.billRepository.removeBill(bill);
    }

    @Override
    public void removeBills(Set<Bill> bills) {
        this.billRepository.removeBills(bills);
    }

    @Override
    public List<Bill> getAllBills() {
        return this.billRepository.getAllBills();
    }

    public Bill getThisMonthBill() {
        Bill bill = this.billRepository.findBillByMonth(LocalDate.now());
        if (bill == null)
            this.billRepository.addBill(new Bill());
        return this.billRepository.findBillByMonth(LocalDate.now());
    }

    @Autowired
    @Override
    public void setBillRepository(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Sandwich orderSandwich(Shop shop) {
        return SandwichMaker.makeSandwich(shop);
    }

}
