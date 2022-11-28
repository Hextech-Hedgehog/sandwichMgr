package sandwich.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.SpringSandwichApplication;
import sandwich.model.Bill;
import sandwich.model.Sandwich;
import sandwich.model.Shop;
import sandwich.utils.SandwichMaker;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = SpringSandwichApplication.class)
class BillServiceTest {

    @Autowired
    private BillService billService;
    @Autowired
    private SandwichRepository sandwichRepository;

    @Mock
    private BillRepository billRepository;

    @Spy
    ArrayList<Bill> bills;

    @Mock
    private Bill bill;

    @BeforeEach
    void setup(@Autowired BillRepository billRepository) {
        this.billService.setBillRepository(billRepository);
        for (int i = 0; i < 5; i++)
            this.bills.add(bill);
    }

    @Test
    void addBill() {
        int size = this.billService.getAllBills().size();
        this.billService.addBill(bill);
        assertThat(this.billService.getAllBills().size(), equalTo(size + 1));
    }

    @Test
    void addBills() {
        int size = this.billService.getAllBills().size();
        this.billService.addBills(bills);
        assertThat(this.billService.getAllBills().size(), greaterThan(size));
    }

    @Test
    void findBillByMonth() {
        LocalDate date = LocalDate.of(1983, 11, 20);
        Bill bill = new Bill();
        bill.setBillDate(date);
        this.billService.setBillRepository(billRepository);
        when(billRepository.findBillByMonth(any(LocalDate.class))).thenReturn(bill);
        assertThat(billService.findBillByMonth(date), equalTo(bill));
        verify(billRepository).findBillByMonth(any(LocalDate.class));
    }

    @Test
    void removeBill() {
        this.billService.addBills(this.bills);
        int size = this.billService.getAllBills().size();
        this.billService.removeBill(bill);
        assertThat(this.billService.getAllBills().size(), Matchers.lessThan(size));
    }

    @Test
    void removeBills() {
        this.billService.addBills(this.bills);
        int size = this.billService.getAllBills().size();
        this.billService.removeBill(bill);
        this.billService.removeBill(bill);
        assertThat(this.billService.getAllBills().size(), Matchers.lessThan(size - 1));
    }

    @Test
    void getAllBills() {
        this.billService.setBillRepository(billRepository);
        when(billRepository.getAllBills()).thenReturn(bills);
        assertThat(this.billService.getAllBills(), equalTo(bills));
    }

    @Test
    void getThisMonthBill() {
        Bill bill = new Bill();
        this.billService.setBillRepository(billRepository);
        when(billRepository.findBillByMonth(any(LocalDate.class))).thenReturn(bill);
        assertThat(billService.findBillByMonth(LocalDate.now()), equalTo(bill));
        verify(billRepository).findBillByMonth(any(LocalDate.class));
    }

    @Test
    void orderSandwich() {
        Shop shop = Shop.PINKYS;
        Sandwich sandwich = sandwichRepository.findSandwich("meat ball");
        try (MockedStatic mock = mockStatic(SandwichMaker.class)) {
            mock.when(() -> SandwichMaker.makeSandwich(any(Shop.class))).thenReturn(sandwich);
            assertThat(this.billService.orderSandwich(shop), equalTo(sandwich));
            mock.verify(() -> SandwichMaker.makeSandwich(any(Shop.class)));
        }
    }
}