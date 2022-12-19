package sandwich.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.LocalDate;
import java.util.List;

@JsonRootName(value = "bill")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillDTO {

    private int billId;
    private List<OrderDTO> orders;
    private LocalDate billDate;

    public BillDTO(){}

    public BillDTO(List<OrderDTO> orders, LocalDate billDate) {
        this.orders = orders;
        this.billDate = billDate;
    }

    public BillDTO(int billId, List<OrderDTO> orders, LocalDate billDate) {
        this(orders, billDate);
        this.billId = billId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }
}
