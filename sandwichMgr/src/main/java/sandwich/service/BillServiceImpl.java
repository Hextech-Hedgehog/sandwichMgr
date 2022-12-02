package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.model.*;
import sandwich.repository.BillJpaRepository;
import sandwich.repository.OrderJpaRepository;
import sandwich.repository.ShopJpaRepository;
import sandwich.utils.SandwichMaker;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BillServiceImpl implements BillService {

    private BillJpaRepository billRepository;
    private OrderJpaRepository orderRepository;
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
        return this.billRepository.findBillByMonthAndYear(date.getMonth().getValue(), date.getYear());
    }

    @Override
    public List<Order> findOrdersByBillAndDate(int billId, LocalDate date) { return this.orderRepository.findOrdersByBillAndDate(billId, date); }

    @Override
    public List<Order> findOrdersByDate(LocalDate date) {
        return this.orderRepository.findOrdersByDate(date);
    }

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
        Bill bill = findBillByMonth(LocalDate.now());
        if (bill == null)
            this.billRepository.save(new Bill());
        return findBillByMonth(LocalDate.now());
    }

    @Autowired
    @Override
    public void setBillRepository(BillJpaRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Autowired
    public void setOrderRepository(OrderJpaRepository orderRepository) { this.orderRepository = orderRepository; }

    @Autowired
    public void setShopJpaRepository(ShopJpaRepository shopRepository) { this.shopRepository = shopRepository; }

    @Override
    public Sandwich orderSandwich(String shopName) {
        Shop shop = this.shopRepository.findShopByShopName(shopName);
        return SandwichMaker.makeSandwich(shop);
    }

    @Override
    public Sandwich orderSandwich(String shopName, Sandwich sandwich) {
        Shop shop = this.shopRepository.findShopByShopName(shopName);
        String sname = sandwich.getSandwichType().getSandwichName();
        if (this.shopRepository.findSandwichByShopAndName(shop.getShopId(), sname) != null) {
            List<Object[]> arr = this.shopRepository.findSandwichByShopAndName(shop.getShopId(), sname);
            sandwich.setSandwichType(new SandwichType(Integer.parseInt(arr.get(0)[0].toString()), arr.get(0)[1].toString()));
            return sandwich;
        }

        return null; //TODO add exception handling
    }

    @Override
    @Transactional
    public void updateBill(Bill bill) {
        this.billRepository.save(bill);
    }

}