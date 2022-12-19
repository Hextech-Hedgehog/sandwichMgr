package sandwich.utils;

import org.springframework.beans.factory.annotation.Autowired;
import sandwich.model.entities.SandwichType;
import sandwich.model.entities.Shop;
import sandwich.repository.ShopJpaRepository;

import java.io.IOException;

public class Menu {

    @Autowired
    private static ShopJpaRepository shopRepository;

    public static void printMenuDetails(Shop shop) throws IOException {
        System.out.println("Welcome to the sandwich manager terminal!");
        System.out.println("We have a few sandwiches options on the menu for you today!");
        System.out.println("They have been freshly prepared by " + shop.getShopName());
        System.out.println("You can even add extras along with your desired sandwich!");
        System.out.println("===========================================================================");
        System.out.println("SANDWICHES: \n");
        printSandwiches(shop);
        System.out.println("===========================================================================");
        System.out.println("EXTRAS: \n");
        System.out.println("As club: Salad, tomato, carrot or pickles, scrambled eggs, mayonnaise");
        System.out.println("Butter");
        System.out.println("===========================================================================\n");
    }

    public static void printSandwiches(Shop shop) {
        shopRepository.findSandwichesByShop(shop.getShopId()).forEach(SandwichType::printContents);
    }

}