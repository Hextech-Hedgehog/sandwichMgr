package sandwich.model;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @SequenceGenerator(name="ingredientGen", sequenceName = "ingredient_iid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredientGen")
    @Column(name="iid")
    private int ingredientId;
    @Column(name="iname")
    private String name;

    public Ingredient(){}

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

    public String toString() {
        return this.name;
    }
}
