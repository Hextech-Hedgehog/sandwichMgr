package repository;

import model.Ingredient;
import model.SandwichType;
import model.Shop;

import java.io.*;
import java.util.*;

public class SandwichTypeFileRepo implements SandwichTypeRepo {

    public static SandwichTypeFileRepo sandwichRepo;


    private SandwichTypeFileRepo() {
    }

    @Override
    public void writeToRepo(List<SandwichType> sandwichTypes, Shop shop) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(shop.getPathWay()))) {
            for (SandwichType sandwichType : sandwichTypes) {
                StringBuilder line = new StringBuilder(sandwichType.getSandwichName() + ";");
                for (int i = 0; i < sandwichType.getIngredients().size(); i++) {
                    line.append(sandwichType.getIngredients().get(i).getName()).append(";");
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<SandwichType> readFromRepo(Shop shop) {
        Set<SandwichType> sandwichTypes = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(shop.getPathWay()))) {
            String sandwichType;
            while ((sandwichType = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(sandwichType, ";");  //  TODO String.split()
                String[] elements = new String[tokenizer.countTokens()];
                for (int i = 0; i < tokenizer.countTokens(); i++) {
                    elements[i] = tokenizer.nextToken();
                }
                List<Ingredient> ingredients = new ArrayList<>();
                for (int i = 1; i < elements.length; i++) {
                    ingredients.add(new Ingredient(elements[i]));
                }
                SandwichType sandwich = new SandwichType(elements[0], ingredients);
                sandwichTypes.add(sandwich);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sandwichTypes;
    }



    public static SandwichTypeFileRepo getInstance() {
        if (sandwichRepo == null) sandwichRepo = new SandwichTypeFileRepo();
        return sandwichRepo;
    }
}
