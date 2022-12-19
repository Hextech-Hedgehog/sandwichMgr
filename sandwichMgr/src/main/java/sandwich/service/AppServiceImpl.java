package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.entities.Bill;
import sandwich.model.entities.Order;
import sandwich.model.entities.Sandwich;
import sandwich.model.entities.User;

import java.time.LocalDate;
import java.util.List;

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
    public List<Order> viewOrdersByBillAndDate(int billId, LocalDate date) {
        return this.billService.findOrdersByBillAndDate(billId, date);
    }

    @Override
    public Sandwich orderSandwich(User user, String shopName, Sandwich sandwich) throws SessionNotFoundException, CourseNotFoundException {
        if ((user = this.userService.findUserByMail(user.getEmail())) != null) {
            Bill bill = this.billService.getThisMonthBill();
            Order order = userService.getOrderByUserForCurrentCourseSession(user);
            sandwich = this.billService.orderSandwich(shopName, sandwich);
            if (sandwich != null && bill.getOrders().contains(order)) {
                sandwich.setUser(user);
                bill.getOrders().stream().filter(o -> o.equals(order)).findFirst().orElse(null).addSandwich(sandwich);
                //order.addSandwich(sandwich);
                //bill.addOrder(order);
                this.billService.updateBill(bill);
            }

        }

        return null; //TODO add exception handling
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
