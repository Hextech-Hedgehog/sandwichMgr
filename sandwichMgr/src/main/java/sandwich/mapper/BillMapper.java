package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.model.dto.BillDTO;
import sandwich.model.entities.Bill;

import java.util.stream.Collectors;

@Component
public class BillMapper {
    public static BillDTO toDto(Bill bill) {
        return new BillDTO(bill.getBillId(), bill.getOrders().stream().map(OrderMapper::toDto).collect(Collectors.toList()), bill.getBillDate());
    }

    public static Bill toBill(BillDTO bill) {
        return new Bill(bill.getBillDate(), bill.getOrders().stream().map(OrderMapper::toOrder).collect(Collectors.toList()));
    }
}
