package sandwich.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "shop")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopDTO {

    private int shopId;
    private String shopName;
    private List<SandwichTypeDTO> sandwiches;

    public ShopDTO(){}

    public ShopDTO(int shopId, String shopName, List<SandwichTypeDTO> sandwiches) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.sandwiches = sandwiches;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<SandwichTypeDTO> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<SandwichTypeDTO> sandwiches) {
        this.sandwiches = sandwiches;
    }

}
