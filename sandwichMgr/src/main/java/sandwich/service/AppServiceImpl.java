package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandwich.model.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.Set;
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private BillService billService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService personService;

    @Override
    public BillService getBillService() {
        return this.billService;
    }

    @Override
    public CourseService getCourseService() {
        return this.courseService;
    }

    @Override
    public UserService getPersonService() {
        return this.personService;
    }

    @Override
    @RolesAllowed("ADMIN")
    public Bill viewBillByDate(User user, LocalDate date) {
        return this.billService.findBillByMonth(date);
    }

    @Override
    @RolesAllowed("ADMIN")
    public Set<Order> viewOrdersByDate(User user, LocalDate date) {
        return this.billService.findBillByMonth(date).getOrdersByDate(date);
    }

    @Override
    public void orderSandwich(User user, String shopName) {
        if (this.personService.getAllUsers().contains(user)) {
            Bill bill = this.billService.getThisMonthBill();
            Order order = personService.getOrderByUserForCurrentCourseSession(user);
            if (bill.containsOrder(order))
                order.addSandwich(this.billService.orderSandwich(Shop.valueOf(shopName), user));
        }
    }

    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setPersonService(UserService personService) {
        this.personService = personService;
    }
}
