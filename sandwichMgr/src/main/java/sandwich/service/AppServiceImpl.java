package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
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
    private PersonService personService;

    @Override
    public BillService getBillService() {
        return this.billService;
    }

    @Override
    public CourseService getCourseService() {
        return this.courseService;
    }

    @Override
    public PersonService getPersonService() {
        return this.personService;
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
        if (this.personService.getAllPeople().contains(user)) {
            Order order = personService.getOrderByUserForCurrentCourseSession(user);
            sandwich = this.billService.orderSandwich(Shop.valueOf(shopName));
            order.addSandwich(sandwich);
            this.billService.getThisMonthBill().addOrder(order);
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
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
