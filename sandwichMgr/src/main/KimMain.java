package main;

import model.Ingredient;
import model.SandwichType;
import model.Shop;
import repository.SandwichTypeFileRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KimMain {

    public static void main(String... args) {

        Ingredient ham = new Ingredient("ham");
        Ingredient onion = new Ingredient("onion");
        Ingredient salad = new Ingredient("salad");
        Ingredient cheese = new Ingredient("cheese");
        Ingredient bacon = new Ingredient("bacon");
        Ingredient mayonnaise = new Ingredient("mayonnaise");
        Ingredient tomato = new Ingredient("tomato");
        Ingredient honey = new Ingredient("honey");

        List<Ingredient> clubIngredients = new ArrayList<>();
        clubIngredients.add(ham); clubIngredients.add(cheese); clubIngredients.add(salad); clubIngredients.add(tomato);

        List<Ingredient> specialIngredients = new ArrayList<>();
        specialIngredients.add(bacon); specialIngredients.add(mayonnaise); specialIngredients.add(honey);

        SandwichType club = new SandwichType("club", clubIngredients);
        SandwichType special = new SandwichType("special", specialIngredients);

        List<SandwichType> sandwichTypes = new ArrayList<>();
        sandwichTypes.add(club); sandwichTypes.add(special);


        SandwichTypeFileRepo.getInstance().writeSandwichesToFile(sandwichTypes, Shop.VLEUGELS);

        Set<SandwichType> sandwiches =  SandwichTypeFileRepo.getInstance().getSandwiches(Shop.VLEUGELS);
        sandwiches.forEach(SandwichType::printContents);

    }
}
