package sandwich.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "sandwichtype")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SandwichTypeDTO {

    private int SandwichTypeId;
    private String sandwichName;
    private List<IngredientDTO> ingredients;

    public SandwichTypeDTO(){}

    public SandwichTypeDTO(int sandwichTypeId, String sandwichName, List<IngredientDTO> ingredients) {
        SandwichTypeId = sandwichTypeId;
        this.sandwichName = sandwichName;
        this.ingredients = ingredients;
    }

    public int getSandwichTypeId() {
        return SandwichTypeId;
    }

    public void setSandwichTypeId(int sandwichTypeId) {
        SandwichTypeId = sandwichTypeId;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public void setSandwichName(String sandwichName) {
        this.sandwichName = sandwichName;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
