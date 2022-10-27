package sandwich.model;

import sandwich.exception.SandwichNotFoundException;
import org.apache.logging.log4j.LogManager;
import sandwich.repository.SandwichTypeFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SandwichType {

    private final String sandwichName;
    private final List<Ingredient> ingredients = new ArrayList<>();

    public SandwichType(String ...sandwichTypeContents) {
        sandwichName = sandwichTypeContents[0];
        IntStream.range(1, sandwichTypeContents.length).forEach(idx -> ingredients.add(new Ingredient(sandwichTypeContents[idx])));
    }

    public SandwichType(String sandwichName, List<Ingredient> ingredients) {
        this.sandwichName = sandwichName;
        this.ingredients.addAll(ingredients);
    }

    public void printContents() {
        System.out.println(this.sandwichName.toUpperCase());
        System.out.print("\tIngredients: ");
        this.ingredients.forEach((i) -> System.out.print(i + " "));
        System.out.println();
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }


    public static SandwichType getSandwichByName(Shop shop, String sandwichName) {
        SandwichType sd = null;
        try {
            sd = SandwichTypeFileRepository.getInstance().getSandwich(shop, sandwichName);
        } catch (SandwichNotFoundException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }

        return sd;
    }

    @Override
    public int hashCode() {
        return this.sandwichName.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SandwichType) {
            SandwichType sandwich = (SandwichType) o;
            return this.sandwichName.equalsIgnoreCase(sandwich.sandwichName);
        }
        return false;
    }

}
