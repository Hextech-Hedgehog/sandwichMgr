package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandwich.model.Bill;
import sandwich.repository.BillRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BillServiceImpl implements BillService {

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

    @Autowired
    public void setBillRepository(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
}
