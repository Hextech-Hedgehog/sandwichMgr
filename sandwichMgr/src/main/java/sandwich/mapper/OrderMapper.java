package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.model.dto.OrderDTO;
import sandwich.model.dto.SandwichDTO;
import sandwich.model.entities.Order;
import sandwich.model.entities.Sandwich;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class OrderMapper {
    public static OrderDTO toDto(Order order) {
        Map<String, List<SandwichDTO>> sandwiches = order.getSandwiches().entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().map(SandwichMapper::toDto).collect(toList())));
        return new OrderDTO(order.getOrderId(), sandwiches, order.getDate());
    }

    public static Order toOrder(OrderDTO order) {
        List<Sandwich> sandwiches = order.getSandwiches().values().stream().flatMap(sdList -> sdList.stream().map(SandwichMapper::toSandwich)).collect(toList());
        return new Order(sandwiches, order.getDate());
    }
}
