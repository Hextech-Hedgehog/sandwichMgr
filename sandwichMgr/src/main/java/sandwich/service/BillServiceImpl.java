package sandwich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sandwich.exception.NonAuthorizedPersonnelException;
import sandwich.factory.SandwichFactory;
import sandwich.model.*;
import sandwich.repository.BillRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Service
@Profile("production")
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;

    @Override
    public void addBill(Bill bill) {
        this.billRepository.addBill(bill);
    }

    @Override
    public void addBills(List<Bill> bills) {
        this.billRepository.addBills(bills);
    }

    @Override
    public Bill findBillByMonth(LocalDate date) {
        return this.billRepository.findBillByMonth(date);
    }

    @Override
    public void removeBill(Bill bill) {
        this.billRepository.removeBill(bill);
    }

    @Override
    public void removeBills(Set<Bill> bills) {
        this.billRepository.removeBills(bills);
    }

    @Override
    public List<Bill> getAllBills() {
        return this.billRepository.getAllBills();
    }

    public Bill getThisMonthBill() {
        Bill bill = this.billRepository.findBillByMonth(LocalDate.now());
        if (bill == null)
            this.billRepository.addBill(new Bill());
        return this.billRepository.findBillByMonth(LocalDate.now());
    }

    @Autowired
    public void setBillRepository(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Sandwich orderSandwich(Shop shop, Person person) throws NonAuthorizedPersonnelException {
        if (person instanceof CourseParticipant) {
            return this.makeSandwich(shop);
        } else
            throw new NonAuthorizedPersonnelException();
    }

    private Sandwich makeSandwich(Shop shop) {
        System.out.println("Please type in the sandwich of your choice ");
        Sandwich sandwich = selectSandwich(shop);
        System.out.println("Here is your sandwich: ");
        sandwich.printContents();
        System.out.println("Have a nice meal!");
        return sandwich;
    }

    private static Sandwich selectSandwich(Shop shop) {
        Scanner sc = new Scanner(System.in);
        Sandwich sandwich;

        while(true) {
            String sandwichName = sc.nextLine();
            SandwichType sd = SandwichType.getSandwichByName(shop, sandwichName);

            if (sd != null) {
                boolean asClub = selectExtra("Would you like your sandwich as a club ?");
                boolean withButter = selectExtra("Do you want butter on your sandwich ?");
                sandwich = SandwichFactory.getSandwichFactory().makeSandwich(sd).asClub(asClub).withButter(withButter).toSandwich();
                break;
            }
            System.out.println("Please type in a correct sandwich option");
        }

        return sandwich;
    }

    private static boolean selectExtra(String extraDescription) {
        System.out.println(extraDescription);
        System.out.println("Type in y or n");
        Scanner sc = new Scanner(System.in);
        boolean extra;

        while(true) {
            String sandwichName = sc.nextLine();
            boolean result = sandwichName.equalsIgnoreCase("y");
            if (result || sandwichName.equalsIgnoreCase("n")) {
                extra = result;
                break;
            }
            System.out.println("Please type in Y or N");
        }

        return extra;
    }
}
