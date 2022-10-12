package model;

import exception.SandwichNotFoundException;

import java.util.ArrayList;
import java.util.List;

public enum SandwichType {

    HAM(new ArrayList<Ingredient>(){{
        add(new Ingredient("Bread"));
        add(new Ingredient("Salad"));
        add(new Ingredient("Ham"));
        add(new Ingredient("Mayonnaise"));
    }}),
    ROAST_BEEF(new ArrayList<Ingredient>(){{
        add(new Ingredient("Bread"));
        add(new Ingredient("Roasted beef"));
        add(new Ingredient("Mayonnaise"));
    }}),
    SALAMI(new ArrayList<Ingredient>(){{
        add(new Ingredient("Bread"));
        add(new Ingredient("Salami"));
        add(new Ingredient("Mayonnaise"));
    }}),
    MEAT_BALL(new ArrayList<Ingredient>(){{
        add(new Ingredient("Bread"));
        add(new Ingredient("Beef"));
        add(new Ingredient("Mayonnaise"));
    }}),
    MARTINO(new ArrayList<Ingredient>(){{
        add(new Ingredient("Bread"));
        add(new Ingredient("Beef"));
        add(new Ingredient("Eggs"));
        add(new Ingredient("Onions"));
        add(new Ingredient("Tomatoes"));
        add(new Ingredient("Pickles"));
        add(new Ingredient("Martino sauce"));
    }});

    private List<Ingredient> ingredients = new ArrayList<>();

    private SandwichType(List<Ingredient> ingredients) {
            this.ingredients.addAll(ingredients);
    }

    public void printInfo() {
        System.out.print("\tIngredients: ");
        this.ingredients.forEach((i) -> System.out.print(i + " "));
        System.out.println();
    }

    public static void printMenu() {
        for(SandwichType sd: SandwichType.values()) {
            System.out.println(sd.name() + ": ");
            sd.printInfo();
        }
    }

    public static SandwichType getSandwichTypeByName(String name) throws SandwichNotFoundException {
        SandwichType sandwichType = null;
        for (SandwichType sd: SandwichType.values()) {
            if (sd.name().equalsIgnoreCase(name))
                sandwichType = sd;
        }

        if (sandwichType == null)
            throw new SandwichNotFoundException("Sandwich " + name + " doesn't exist");
        return sandwichType;
    }

}
