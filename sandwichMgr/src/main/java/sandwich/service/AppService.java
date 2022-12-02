package sandwich.service;

import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.Bill;
import sandwich.model.Order;
import sandwich.model.Sandwich;
import sandwich.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AppService {

    BillService getBillService();
    CourseService getCourseService();
    UserService getUserService();
    void setBillService(BillService billService);
    void setUserService(UserService personService);
    void setCourseService(CourseService courseService);
    Bill viewBillByDate(LocalDate date);
    List<Order> viewOrdersByBillAndDate(int billId, LocalDate date);
    Sandwich orderSandwich(User user, String shopName, Sandwich sandwich) throws SessionNotFoundException, CourseNotFoundException;

}
