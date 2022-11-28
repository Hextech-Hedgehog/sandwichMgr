package sandwich.service;

import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.Bill;
import sandwich.model.Order;
import sandwich.model.Sandwich;
import sandwich.model.User;

import java.time.LocalDate;
import java.util.Set;

public interface AppService {

    BillService getBillService();
    CourseService getCourseService();
    UserService getUserService();
    void setBillService(BillService billService);
    void setUserService(UserService personService);
    void setCourseService(CourseService courseService);
    Bill viewBillByDate(LocalDate date);
    Set<Order> viewOrdersByDate(LocalDate date);
    Sandwich orderSandwich(User user, String shopName) throws SessionNotFoundException, CourseNotFoundException;

}
