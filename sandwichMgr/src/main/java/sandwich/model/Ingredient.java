package sandwich.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id @SequenceGenerator(name = "ingredient_generator", sequenceName = "ingredient_iid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_generator")
    @Column(name = "iid")               private int ingredientId;
    @Column(name = "iname")             private String name;

    @ManyToMany
    @JoinTable(name = "sandwichingredients",
            joinColumns = @JoinColumn(name = "si_iid"),
            inverseJoinColumns = @JoinColumn(name = "si_stid") )
    private List<SandwichType> sandwichTypes = new ArrayList<>();


    public Ingredient() {

    }

    public Ingredient(String name){
        this.name = name;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SandwichType> getSandwichTypes() {
        return sandwichTypes;
    }

    public void setSandwichTypes(List<SandwichType> sandwichTypes) {
        this.sandwichTypes = sandwichTypes;
    }

    public String toString() {
        return this.name;
    }
}
