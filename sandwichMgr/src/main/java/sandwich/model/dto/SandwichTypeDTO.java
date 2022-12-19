package sandwich.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "sandwichtype")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SandwichTypeDTO {

    private int SandwichTypeId;
    private String sandwichName;
    private String shop;
    private List<IngredientDTO> ingredients;

    public SandwichTypeDTO(){}

    public SandwichTypeDTO(String sandwichName, String shop, List<IngredientDTO> ingredients) {
        this.sandwichName = sandwichName;
        this.shop = shop;
        this.ingredients = ingredients;
    }

    public SandwichTypeDTO(int sandwichTypeId, String sandwichName, String shop, List<IngredientDTO> ingredients) {
        this(sandwichName, shop, ingredients);
        SandwichTypeId = sandwichTypeId;
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

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
