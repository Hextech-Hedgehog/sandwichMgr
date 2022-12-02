package sandwich.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ingredient")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientDTO {

    private String name;

    public IngredientDTO() {}

    public IngredientDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
