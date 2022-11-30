package sandwich.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@JsonRootName("order")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {

    private int orderId;
    private Map<String, List<SandwichDTO>> sandwiches;
    private LocalDate date;

    public OrderDTO(){}

    public OrderDTO(int orderId, Map<String, List<SandwichDTO>> sandwiches, LocalDate date) {
        this.orderId = orderId;
        this.sandwiches = sandwiches;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Map<String, List<SandwichDTO>> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(Map<String, List<SandwichDTO>> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
