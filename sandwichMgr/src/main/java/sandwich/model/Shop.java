package sandwich.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @SequenceGenerator(name="shopGen", sequenceName = "shop_shid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopGen")
    @Column(name="shid")
    private int shopId;
    @Column(name = "shname")
    private String shopName;
    @OneToMany(targetEntity = SandwichType.class, cascade = {MERGE, PERSIST})
    @JoinColumn(name="st_shid")
    private List<SandwichType> sandwiches;

    public Shop(){}

    public Shop(String shopName) {
        this.shopId = shopId;
        this.shopName = shopName;
    }

    public Shop(String shopName, List<SandwichType> sandwiches) {
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

    public List<SandwichType> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<SandwichType> sandwiches) {
        this.sandwiches = sandwiches;
    }
}