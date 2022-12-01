package sandwich.dto;

import sandwich.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class SandwichTypeDTO {

    private String sandwichName;
    private List<Ingredient> ingredients = new ArrayList<>();

    public SandwichTypeDTO() {
    }

    public SandwichTypeDTO(String sandwichName, List<Ingredient> ingredients) {
        this.sandwichName = sandwichName;
        this.ingredients = ingredients;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public void setSandwichName(String sandwichName) {
        this.sandwichName = sandwichName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }



}
