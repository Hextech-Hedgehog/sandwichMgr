package utils;

import model.SandwichType;

public class Menu {

    public static void printMenuDetails() {
        System.out.println("Welcome to the sandwich manager terminal!");
        System.out.println("We have a few sandwiches options on the menu for you today!");
        System.out.println("You can even add extras along with your desired sandwich!");
        System.out.println("===========================================================================");
        System.out.println("SANDWICHES: \n");
        SandwichType.printMenu();
        System.out.println("===========================================================================");
        System.out.println("EXTRAS: \n");
        System.out.println("As club: Salad, tomato, carrot or pickles, scrambled eggs, mayonnaise");
        System.out.println("Butter");
        System.out.println("===========================================================================\n");
    }

}
