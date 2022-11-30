package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.model.*;
import sandwich.utils.repository.BillJpaRepository;
import sandwich.utils.repository.ShopJpaRepository;
import sandwich.utils.SandwichMaker;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Profile("production")
public class BillServiceImpl implements BillService {

    private BillJpaRepository billRepository;
    private ShopJpaRepository shopRepository;

    @Override
    public void addBill(Bill bill) {
        this.billRepository.save(bill);
    }

    @Override
    public void addBills(List<Bill> bills) {
        this.billRepository.saveAll(bills);
    }

    @Override
    public Bill findBillByMonth(LocalDate date) {
        return this.billRepository.findBillByMonth(date);
    }

    @Override
    public List<Order> findOrdersByBillDate(LocalDate date) { return this.billRepository.findOrdersByBillDate(date); }

    @Override
    public void removeBill(Bill bill) {
        this.billRepository.delete(bill);
    }

    @Override
    public void removeBills(Set<Bill> bills) {
        this.billRepository.deleteAll(bills);
    }

    @Override
    public List<Bill> getAllBills() {
        return this.billRepository.findAll();
    }

    public Bill getThisMonthBill() {
        Bill bill = this.billRepository.findBillByMonth(LocalDate.now());
        if (bill == null)
            this.billRepository.save(new Bill());
        return this.billRepository.findBillByMonth(LocalDate.now());
    }

    @Autowired
    @Override
    public void setBillRepository(BillJpaRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Autowired
    public void setShopJpaRepository(ShopJpaRepository shopRepository) { this.shopRepository = shopRepository; }

    @Override
    public Sandwich orderSandwich(String shopName) {
        Shop shop = this.shopRepository.findShopByShopName(shopName);
        return SandwichMaker.makeSandwich(shop);
    }

}
