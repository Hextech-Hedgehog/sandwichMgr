package sandwich.dto;

import sandwich.model.SandwichType;

import java.util.ArrayList;
import java.util.List;

public class IngredientDTO {

    private String name;
    private List<SandwichType> sandwichTypes = new ArrayList<>();

    public IngredientDTO() {
    }

    public IngredientDTO(String name) {
        this.name = name;
    }

    public IngredientDTO(String name, List<SandwichType> sandwichTypes) {
        this.name = name;
        this.sandwichTypes = sandwichTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SandwichType> getSandwichTypes() {
        return sandwichTypes;
    }

    public void setSandwichTypes(List<SandwichType> sandwichTypes) {
        this.sandwichTypes = sandwichTypes;
    }


}
