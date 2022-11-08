/*package main;


import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Map<sandwich.model.Sandwich, Integer> sandwiches = new HashMap<Sandwich, Integer>(){{
            put(new sandwich.model.Sandwich(sandwich.model.SandwichType.getSandwichByName(Shop.PINKYS, "meat ball"), true, false), 5);
            put(new sandwich.model.Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "martino"), false, false), 2);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "ham"), true, true), 1);
        }};
        Map<Sandwich, Integer> sandwiches2 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "salami"), false, false), 4);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "ham"), true, false), 2);
        }};
        Map<Sandwich, Integer> sandwiches3 = new HashMap<Sandwich, Integer>(){{
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "roast beef"), true, false), 1);
            put(new Sandwich(SandwichType.getSandwichByName(Shop.PINKYS, "martino"), false, false), 1);
        }};
        Set<Order> orders = new HashSet<>(){{
            add(new Order(sandwiches, LocalDate.of(2022, 10, 10)));
            add(new Order(sandwiches2, LocalDate.of(2022, 10, 11)));
            add(new Order(sandwiches3, LocalDate.of(2022, 10, 12)));
        }};
        Bill bill = new Bill(orders);

        Course java = new Course("Java Se programming");
        Person teacher = new StaffMember("Albus");
        Person admin = new Admin("Bob");
        List<CourseParticipant> participants = new ArrayList<CourseParticipant>(){{
            add(new CourseParticipant("Harry"));
            add(new CourseParticipant("Peter"));
            add(new CourseParticipant("Mary"));
            add(new CourseParticipant("Gwen"));
            add(new CourseParticipant("Charlie"));
            add(new CourseParticipant("Archibald"));
        }};
        java.addParticipants(participants);

        List<Session> sessions = new ArrayList<Session>(){{
            add(new Session(java.getName(), (StaffMember) teacher, LocalDate.of(2022, 10, 13), LocalDate.of(2022, 10, 17)));
            add(new Session(java.getName(), (StaffMember) teacher, LocalDate.of(2022, 10, 18), LocalDate.of(2022, 10, 19)));
            add(new Session(java.getName(), (StaffMember) teacher, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 31)));
            add(new Session(java.getName(), (StaffMember) teacher, LocalDate.of(2022, 11, 1), LocalDate.of(2022, 11, 26)));
        }};
        java.setSessions(sessions);

        Menu.printMenuDetails(Shop.PINKYS);
        try {
            java.getCourseParticipantByName("Harry").orderSandwich(Shop.PINKYS);
        } catch(ParticipantNotFoundException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }
        try {
            bill.addOrder(java.getSessionByDate(LocalDate.now()).getDailyOrder());
        } catch (SessionNotFoundException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }
        bill.viewOrderByDate(LocalDate.now());
    }

}*/
