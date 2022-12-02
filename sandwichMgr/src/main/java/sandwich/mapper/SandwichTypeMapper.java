package sandwich.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sandwich.dto.IngredientDTO;
import sandwich.dto.SandwichTypeDTO;
import sandwich.model.Ingredient;
import sandwich.model.SandwichType;
import sandwich.repository.ShopJpaRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SandwichTypeMapper {

    private static ShopJpaRepository shopRepo;
    private ShopJpaRepository shopRepository;
    public static SandwichTypeDTO toDto(SandwichType sandwich) {
        String shopName = shopRepo.findShopOfSandwichType(sandwich.getSandwichTypeId()).getShopName();
        return new SandwichTypeDTO(sandwich.getSandwichName(), shopName,sandwich.getIngredients().stream().map(IngredientMapper::toDto).collect(Collectors.toList()));
    }

    public static SandwichType toSandwichType(SandwichTypeDTO sandwich) {
        List<Ingredient> ingredients = sandwich.getIngredients() != null ? sandwich.getIngredients().stream().map(IngredientMapper::toIngredient).collect(Collectors.toList()) : null;
        return new SandwichType(sandwich.getSandwichName(), ingredients);
    }

    @Autowired
    public void setShopRepository(ShopJpaRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @PostConstruct
    private void initShopRepo() {
        shopRepo = this.shopRepository;
    }
}
