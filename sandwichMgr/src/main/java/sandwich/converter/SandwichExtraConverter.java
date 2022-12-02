package sandwich.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SandwichExtraConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        return (aBoolean != null || aBoolean) ? "y": "n" ;
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return s.equalsIgnoreCase("y");
    }
}
