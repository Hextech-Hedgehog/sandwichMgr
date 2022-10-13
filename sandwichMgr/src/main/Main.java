package main;

import model.Course;
import model.CourseParticipant;
import model.Person;
import utils.Menu;

public class Main {

    public static void main(String[] args) {
        Course java = new Course("Java Se programming");
        Person person = new CourseParticipant("Harry", java);
        Menu.printMenuDetails();
        person.orderSandwich();
    }

}
