package sandwich.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.model.entities.Bill;
import sandwich.model.entities.Order;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BillJpaRepositoryTest {

    @Autowired
    private BillJpaRepository billRepository;
    @Autowired
    private OrderJpaRepository orderRepository;

    @Test
    @Transactional
    void addBill() {
        int size = billRepository.findAll().size();
        billRepository.addBill(LocalDate.now());
        int editedSize = billRepository.findAll().size();
        assertEquals(size, editedSize - 1);
    }

    @Test
    @Transactional
    void findBillByMonth() {
        Bill bill = new Bill(LocalDate.now());
        billRepository.save(bill);
        assertEquals(bill, billRepository.findBillByMonthAndYear(bill.getBillDate().getMonth().getValue(), bill.getBillDate().getYear()));
    }

    @Test
    @Transactional
    void removeBill() {
        billRepository.addBill(LocalDate.now());
        int size = billRepository.findAll().size();
        billRepository.removeBill(billRepository.findBillByMonthAndYear(LocalDate.now().getMonth().getValue(), LocalDate.now().getYear()).getBillId());
        int editedSize = billRepository.findAll().size();
        assertEquals(size, editedSize + 1);
    }

    @Test
    @Transactional
    void getBills() {
        int initialSize = billRepository.findAll().size();
        List<Bill> bills = new ArrayList<Bill>(){{
            add(new Bill(LocalDate.now()));
            add(new Bill(LocalDate.of(1996, 7, 14)));
            add(new Bill(LocalDate.of(2001, 4, 2)));
            add(new Bill(LocalDate.of(2005, 10, 17)));
        }};
        billRepository.saveAll(bills);
        int editedSize = billRepository.findAll().size();
        assertEquals(initialSize + 4, editedSize);
    }

    @Test
    @Transactional
    void findOrdersByBillDate() {
        Bill bill = new Bill(LocalDate.of(2009, 11, 21));
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(LocalDate.of(2009, 11, 21)));
        orders.add(new Order(LocalDate.of(2010, 9, 11)));
        orders.add(new Order(LocalDate.of(2010, 9, 11)));
        orders.add(new Order(LocalDate.of(2009, 11, 21)));
        orders.add(new Order(LocalDate.of(2011, 8, 12)));
        bill.addOrders(orders);
        billRepository.save(bill);
        bill = billRepository.findBillByMonthAndYear(11, 2009);

        assertEquals(2, orderRepository.findOrdersByBillAndDate(bill.getBillId(), LocalDate.of(2009, 11, 21)).size());
    }
}