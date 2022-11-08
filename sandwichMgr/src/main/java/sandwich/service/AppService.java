package sandwich.service;

import sandwich.model.Bill;
import sandwich.model.Order;
import sandwich.model.User;

import java.time.LocalDate;
import java.util.Set;

public interface AppService {

    BillService getBillService();
    CourseService getCourseService();
    UserService getPersonService();
    Bill viewBillByDate(User user, LocalDate date);
    Set<Order> viewOrdersByDate(User user, LocalDate date);
    void orderSandwich(User user, String shopName);

}
