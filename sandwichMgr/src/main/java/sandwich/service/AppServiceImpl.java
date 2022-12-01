package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.*;

import java.time.LocalDate;
import java.util.Set;
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private BillService billService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @Override
    public BillService getBillService() {
        return this.billService;
    }

    @Override
    public CourseService getCourseService() {
        return this.courseService;
    }

    @Override
    public UserService getUserService() {
        return this.userService;
    }

    @Override
    public Bill viewBillByDate(LocalDate date) {
        return this.billService.findBillByMonth(date);
    }

    @Override
    public Set<Order> viewOrdersByDate(LocalDate date) {
        return this.billService.findBillByMonth(date).getOrdersByDate(date);
    }

    @Override
    public Sandwich orderSandwich(User user, String shopName) throws SessionNotFoundException, CourseNotFoundException {
        Sandwich sandwich = null;
        if (this.userService.getAllUsers().contains(user)) {
            Bill bill = this.billService.getThisMonthBill();
            Order order = userService.getSandwichOrderByUserForCurrentCourseSession(user);
            sandwich = this.billService.orderSandwich(Shop.valueOf(shopName));
            order.addSandwich(sandwich);
            bill.addOrder(order);
        }

        return sandwich;
    }

    @Override
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    @Override
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }


    @Override
    public void setUserService(UserService personService) {
        this.userService = personService;
    }
}
