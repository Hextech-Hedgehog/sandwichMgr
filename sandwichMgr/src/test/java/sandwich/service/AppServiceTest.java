package sandwich.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sandwich.SpringSandwichApplication;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.PersonNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.*;
import sandwich.repository.SandwichRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringSandwichApplication.class)
class AppServiceTest {

    @Autowired
    AppService appService;

    @Autowired
    SandwichRepository sandwichRepository;

    @Mock
    BillService billService;

    @Spy
    List<Bill> bills;

    @Mock
    Bill bill;

    @BeforeEach
    void setUp(@Autowired BillService billService) {
        this.appService.setBillService(billService);
        for (int i = 0; i < 5; i++)
            this.bills.add(bill);
    }

    @Test
    void viewBillByDate() {
        this.appService.setBillService(billService);
        LocalDate date = LocalDate.of(1983, 11, 20);
        Bill bill = new Bill();
        bill.setBillDate(date);
        when(billService.findBillByMonth(any(LocalDate.class))).thenReturn(bill);
        assertThat(this.appService.viewBillByDate(date), equalTo(bill));
    }

    @Test
    void viewOrdersByDate() {
        Bill bill = this.appService.getBillService().getAllBills().get(0);
        Set<Order> temp = bill.getOrders().entrySet().stream().findFirst().get().getValue();
        Order order = temp.iterator().next();
        Set<Order> orders = new HashSet<Order>(){{
            add(order);
        }};
        assertThat(this.appService.viewOrdersByDate(order.getDate()), equalTo(orders));
    }

    @Test
    void orderSandwich() throws PersonNotFoundException, CourseNotFoundException, SessionNotFoundException {
        this.appService.setBillService(billService);
        User user = this.appService.getPersonService().findUserByName("Bob");
        Sandwich sandwich = this.sandwichRepository.findSandwich("meat ball");
        when(billService.orderSandwich(any(Shop.class))).thenReturn(sandwich);
        when(billService.getThisMonthBill()).thenReturn(new Bill());
        assertThat(this.appService.orderSandwich(user, "PINKYS"), equalTo(sandwich));
    }
}