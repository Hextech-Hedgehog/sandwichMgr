package sandwich.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ingredient")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientDTO {

    private int ingredientId;
    private String name;

    public IngredientDTO() {}

    public IngredientDTO(int ingredientId, String name) {
        this.ingredientId = ingredientId;
        this.name = name;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
