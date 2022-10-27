package sandwich.service;

import sandwich.exception.NonAuthorizedPersonnelException;
import sandwich.exception.SessionNotFoundException;
import sandwich.model.*;

import java.time.LocalDate;
import java.util.Set;

public class AppServiceImpl implements AppService {

    private BillService billService;
    private CourseService courseService;
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
    public Bill viewBillByDate(Person person, LocalDate date) throws NonAuthorizedPersonnelException {
        if (person instanceof Admin)
            return this.billService.findBillByMonth(date);
        throw new NonAuthorizedPersonnelException();
    }

    @Override
    public Set<Order> viewOrdersByDate(Person person, LocalDate date) throws NonAuthorizedPersonnelException {
        if (person instanceof Admin)
            return this.billService.findBillByMonth(date).getOrdersByDate(date);
        throw new NonAuthorizedPersonnelException();
    }

    @Override
    public void orderSandwich(Person person, String shopName) throws NonAuthorizedPersonnelException {
        if (this.personService.getAllPeople().contains(person)) {
            Bill bill = this.billService.getThisMonthBill();
            Order order = personService.getOrderByCurrentCourseSession(person);
            if (bill.containsOrder(order))
                order.addSandwich(this.billService.orderSandwich(Shop.valueOf(shopName), person));
        }
    }

    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
