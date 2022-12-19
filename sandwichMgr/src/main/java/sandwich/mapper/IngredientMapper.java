package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.model.dto.IngredientDTO;
import sandwich.model.entities.Ingredient;

@Component
public class IngredientMapper {
    public static IngredientDTO toDto(Ingredient ingredient) {
        return new IngredientDTO(ingredient.getName());
    }

    public static Ingredient toIngredient(IngredientDTO ingredient) {
        return new Ingredient(ingredient.getName());
    }
}
