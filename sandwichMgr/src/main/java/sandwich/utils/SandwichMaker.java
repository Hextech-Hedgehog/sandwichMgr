package sandwich.utils;

import sandwich.factory.SandwichFactory;
import sandwich.model.Sandwich;
import sandwich.model.SandwichType;
import sandwich.model.Shop;

import java.util.Scanner;

public class SandwichMaker {

    public static Sandwich makeSandwich(Shop shop) {
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
            SandwichType sd = shop.getSandwiches().stream().filter(s -> s.getSandwichName().equals(sandwichName)).findFirst().orElse(null);

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
