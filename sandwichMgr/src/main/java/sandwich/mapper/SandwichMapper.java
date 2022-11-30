package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.dto.SandwichDTO;
import sandwich.model.Sandwich;

@Component
public class SandwichMapper {
    public static SandwichDTO toDto(Sandwich sandwich) {
        return new SandwichDTO(sandwich.getSandwichId(), SandwichTypeMapper.toDto(sandwich.getSandwichType()), sandwich.isAsClub(), sandwich.isWithButter(), sandwich.getOptionalRequirement(), UserMapper.toDto(sandwich.getUser()));
    }

    public static Sandwich toSandwich(SandwichDTO sandwich) {
        return new Sandwich(SandwichTypeMapper.toSandwichType(sandwich.getSandwichType()), sandwich.isAsClub(), sandwich.isWithButter(), sandwich.getOptionalRequirement(), UserMapper.toUser(sandwich.getUser()));
    }
}
