package sandwich.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.utils.repository.BillJpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BillJpaRepositoryTest {

    @Autowired
    private BillJpaRepository billRepository;

    @Test
    @Transactional
    void addBill() {
        int size = billRepository.findAll().size();
        billRepository.addBill(LocalDate.now());
        int editedSize = billRepository.findAll().size();
        assertEquals(size, editedSize - 1);
    }

    @Test
    void findBillByMonth() {
    }

    @Test
    void removeBill() {
    }

    @Test
    void getBills() {
    }

    @Test
    void findOrdersByBillDate() {
    }
}