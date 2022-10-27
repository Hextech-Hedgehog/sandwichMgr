package sandwich.service;

import sandwich.exception.NonAuthorizedPersonnelException;
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
        if (person instanceof StaffMember)
            return this.billService.findBillByMonth(date);
        throw new NonAuthorizedPersonnelException();
    }

    @Override
    public Set<Order> viewOrdersByDate(Person person, LocalDate date) throws NonAuthorizedPersonnelException {
        if (person instanceof StaffMember)
            return this.billService.findBillByMonth(date).getOrdersByDate(date);
        throw new NonAuthorizedPersonnelException();
    }

    @Override
    public void orderSandwich(Person person, String shopName, Sandwich sandwich) throws NonAuthorizedPersonnelException {
        //get the monthly bill, create it if it doesn't exist
        // get the session of the day, if it doesn't exist or the person is not authorized to order, cancel
        // add the sandwich to the session order and that order if is a new one, add it to the monthly bill
        //if the order is updated, so should it in the bill
        //if (person instanceof StaffMember)
            //this
            //this.billService.findBillByMonth(LocalDate.now()).addOrder(new Order());
        throw new NonAuthorizedPersonnelException();
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
