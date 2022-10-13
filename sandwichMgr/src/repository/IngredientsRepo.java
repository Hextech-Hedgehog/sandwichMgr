package repository;

import model.Ingredient;
import model.SandwichType;

import java.util.Set;

public interface IngredientsRepo {

    void findIngredientsForSandwich(SandwichType sandwichType);
    void writeIngredientsToRepo(Set<Ingredient> ingredients);


}
