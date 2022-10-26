package sandwich.repository;

import sandwich.exception.SandwichNotFoundException;
import sandwich.model.Ingredient;
import sandwich.model.SandwichType;
import sandwich.model.Shop;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.util.*;

public class SandwichTypeFileRepo implements SandwichTypeRepo {

    public static SandwichTypeFileRepo sandwichRepo;

    private SandwichTypeFileRepo() {}

    @Override
    public void writeSandwichesToFile(List<SandwichType> sandwichTypes, Shop shop) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(shop.getPathWay()))) {
            for (SandwichType sandwichType : sandwichTypes) {
                StringBuilder line = new StringBuilder(sandwichType.getSandwichName() + ";");
                for (int i = 0; i < sandwichType.getIngredients().size(); i++) {
                    line.append(sandwichType.getIngredients().get(i).getName()).append(";");
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }
    }

    @Override
    public Set<SandwichType> getSandwiches(Shop shop) {
        Set<SandwichType> sandwichTypes = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(shop.getPathWay()))) {
            String sandwichType;
            while ((sandwichType = reader.readLine()) != null) {
                String[] elements = sandwichType.split(";");
                List<Ingredient> ingredients = new ArrayList<>();
                for (int i = 1; i < elements.length; i++) {
                    ingredients.add(new Ingredient(elements[i]));
                }
                SandwichType sandwich = new SandwichType(elements[0], ingredients);
                sandwichTypes.add(sandwich);
            }
        } catch (IOException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }
        return sandwichTypes;
    }

    @Override
    public SandwichType getSandwich(Shop shop, String sandwichName) throws SandwichNotFoundException {
        SandwichType sandwichType = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(shop.getPathWay()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(";");
                if (elements[0].equalsIgnoreCase(sandwichName)) {
                    List<Ingredient> ingredients = new ArrayList<>();
                    for (int i = 1; i < elements.length; i++)
                        ingredients.add(new Ingredient(elements[i]));
                    sandwichType = new SandwichType(elements[0], ingredients);
                    break;
                }
            }
        } catch (IOException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }
        if (sandwichType == null)
            throw new SandwichNotFoundException("Sandwich " + sandwichName + " doesn't exist.");
        return sandwichType;
    }

    public void printSandwiches(Shop shop) {
        for (SandwichType sandwich: this.getSandwiches(shop))
            sandwich.printContents();
    }

    public static SandwichTypeFileRepo getInstance() {
        if (sandwichRepo == null)
            sandwichRepo = new SandwichTypeFileRepo();
        return sandwichRepo;
    }

}
