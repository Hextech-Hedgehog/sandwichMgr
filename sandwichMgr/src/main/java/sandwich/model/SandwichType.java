package sandwich.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Table(name = "course")
public class SandwichType {

    @Id @SequenceGenerator(name = "sandwichtype_generator", sequenceName = "course_cid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sandwichtype_generator")
    @Column(name = "stid")              private int sandwichTypeId;
    @Column(name = "stname")            private String sandwichName;

    @ManyToMany(mappedBy = "sandwichTypes")
    private List<Ingredient> ingredients = new ArrayList<>();

    public SandwichType() {
    }

    public SandwichType(String... sandwichTypeContents) {
        sandwichName = sandwichTypeContents[0];
        IntStream.range(1, sandwichTypeContents.length).forEach(idx -> ingredients.add(new Ingredient(sandwichTypeContents[idx])));
    }

    public SandwichType(String sandwichName, List<Ingredient> ingredients) {
        this.sandwichName = sandwichName;
        this.ingredients.addAll(ingredients);
    }

    public void printContents() {
        System.out.println(this.sandwichName.toUpperCase());
        System.out.print("\tIngredients: ");
        this.ingredients.forEach((i) -> System.out.print(i + " "));
        System.out.println();
    }

    public int getSandwichTypeId() {
        return sandwichTypeId;
    }

    public void setSandwichTypeId(int sandwichTypeId) {
        this.sandwichTypeId = sandwichTypeId;
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

}
