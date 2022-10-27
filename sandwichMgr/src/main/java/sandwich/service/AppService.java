package sandwich.service;

import sandwich.exception.NonAuthorizedPersonnelException;
import sandwich.model.Bill;
import sandwich.model.Order;
import sandwich.model.Person;
import sandwich.model.Sandwich;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AppService {

    BillService getBillService();
    CourseService getCourseService();
    PersonService getPersonService();
    Bill viewBillByDate(Person person, LocalDate date) throws NonAuthorizedPersonnelException;
    Set<Order> viewOrdersByDate(Person person, LocalDate date) throws NonAuthorizedPersonnelException;
    void orderSandwich(Person person, String shopName) throws NonAuthorizedPersonnelException;

}
