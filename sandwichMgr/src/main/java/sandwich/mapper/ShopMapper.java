package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.model.dto.ShopDTO;
import sandwich.model.entities.Shop;

import java.util.stream.Collectors;

@Component
public class ShopMapper {
    public static ShopDTO toDto(Shop shop) {
        return new ShopDTO(shop.getShopId(), shop.getShopName(), shop.getSandwiches().stream().map(SandwichTypeMapper::toDto).collect(Collectors.toList()));
    }

    public static Shop toShop(ShopDTO shop) {
        return new Shop(shop.getShopName(), shop.getSandwiches().stream().map(SandwichTypeMapper::toSandwichType).collect(Collectors.toList()));
    }
}
