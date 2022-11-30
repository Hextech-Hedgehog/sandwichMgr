package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.dto.IngredientDTO;
import sandwich.dto.SandwichTypeDTO;
import sandwich.model.SandwichType;

import java.util.stream.Collectors;

@Component
public class SandwichTypeMapper {
    public static SandwichTypeDTO toDto(SandwichType sandwich) {
        return new SandwichTypeDTO(sandwich.getSandwichTypeId(), sandwich.getSandwichName(), sandwich.getIngredients().stream().map(IngredientMapper::toDto).collect(Collectors.toList()));
    }

    public static SandwichType toSandwichType(SandwichTypeDTO sandwich) {
        return new SandwichType(sandwich.getSandwichName(), sandwich.getIngredients().stream().map(IngredientMapper::toIngredient).collect(Collectors.toList()));

    }
}
