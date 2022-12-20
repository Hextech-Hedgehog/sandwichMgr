package sandwich.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Table(name="sandwichtype")
public class SandwichType {

    @Id
    @SequenceGenerator(name="sandwichTypeGen", sequenceName = "sandwich_type_stid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sandwichTypeGen")
    @Column(name="stid")
    private int sandwichTypeId;
    @Column(name="stname")
    private String sandwichName;
    @ManyToMany
    @JoinTable(name = "SANDWICHINGREDIENTS",
            joinColumns = @JoinColumn(name = "si_stid"),
            inverseJoinColumns = @JoinColumn(name = "si_iid"))
    private List<Ingredient> ingredients = new ArrayList<>();

    public SandwichType(){}

    public SandwichType(String ...sandwichTypeContents) {
        sandwichName = sandwichTypeContents[0];
        IntStream.range(1, sandwichTypeContents.length).forEach(idx -> ingredients.add(new Ingredient(sandwichTypeContents[idx])));
    }

    public SandwichType(int sandwichTypeId, String sandwichName) {
        this.sandwichTypeId = sandwichTypeId;
        this.sandwichName = sandwichName;
    }

    public SandwichType(String sandwichName, List<Ingredient> ingredients) {
        this.sandwichName = sandwichName;
        if (ingredients != null)
            this.ingredients.addAll(ingredients);
    }

    public void printContents() {
        System.out.println(this.sandwichName.toUpperCase());
        System.out.print("\tIngredients: ");
        this.ingredients.forEach((i) -> System.out.print(i + " "));
        System.out.println();
    }

    //TODO move function inside repo
    /*public static SandwichType getSandwichByName(Shop shop, String sandwichName) {
        SandwichType sd = null;
        try {
            sd = new SandwichTypeFileRepository().getSandwich(shop, sandwichName);
        } catch (SandwichNotFoundException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }
        return sd;
    }*/

    @Override
    public int hashCode() {
        return this.sandwichName.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SandwichType) {
            SandwichType sandwich = (SandwichType) o;
            return this.sandwichName.equalsIgnoreCase(sandwich.sandwichName);
        }
        return false;
    }

    public int getSandwichTypeId() {
        return sandwichTypeId;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public void setSandwichName(String sandwichName) {
        this.sandwichName = sandwichName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}