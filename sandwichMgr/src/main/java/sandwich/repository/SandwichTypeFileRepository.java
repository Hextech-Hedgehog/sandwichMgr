package sandwich.repository;

import org.springframework.stereotype.Repository;
import sandwich.exception.SandwichNotFoundException;
import sandwich.model.Ingredient;
import sandwich.model.SandwichType;
import sandwich.model.Shop;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.util.*;

@Repository
public class SandwichTypeFileRepository implements SandwichTypeRepository {

    @Override
    public void addSandwichType(Shop shop, SandwichType sandwich) {
        try (PrintWriter pw = new PrintWriter(shop.getPathWay())) {
            String sandwichLine = sandwich.getSandwichName() + ";";
            for (Ingredient ingredient: sandwich.getIngredients())
                sandwichLine += ingredient.getName() + ";";
            pw.write(sandwichLine);
        } catch(IOException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }
    }

    @Override
    public void addSandwichTypes(Shop shop, List<SandwichType> sandwichTypes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(shop.getPathWay()))) {
            for (SandwichType sandwichType : sandwichTypes) {
                StringBuilder line = new StringBuilder(sandwichType.getSandwichName() + ";");
                for (int i = 0; i < sandwichType.getIngredients().size(); i++) {
                    line.append(sandwichType.getIngredients().get(i).getName()).append(";");
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            // catch in aspect :
            LogManager.getLogger("error").error(e.getMessage());
        }
    }

    @Override
    public Set<SandwichType> getSandwichesFromShop(Shop shop) {
        Set<SandwichType> sandwichTypes = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(shop.getPathWay()))) {
            String sandwichType;
            while ((sandwichType = reader.readLine()) != null) {
                String[] elements = sandwichType.split(";");
                SandwichType sandwich = new SandwichType(elements);
                sandwichTypes.add(sandwich);
            }
        } catch (IOException e) {
            // catch in aspect: LogManager.getLogger("error").error("your message");
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
                    sandwichType = new SandwichType(elements);
                    break;
                }
            }
        } catch (IOException e) {
            // catch in aspect LogManager.getLogger("error").error(e.getMessage());
        }
        if (sandwichType == null)           // TODO Aspect Exception Logger
            throw new SandwichNotFoundException("Sandwich " + sandwichName + " doesn't exist.");
        return sandwichType;
    }

    @Override
    public void removeSandwichType(Shop shop, SandwichType sandwich) {
        List<SandwichType> sandwiches = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(shop.getPathWay()))) {
            String str;
            while ((str = br.readLine()) != null) {
                SandwichType sandwichType = new SandwichType(str.split(";"));
                if (!sandwichType.equals(sandwich))
                    sandwiches.add(sandwichType);
            }
            this.addSandwichTypes(shop, sandwiches);
        } catch (IOException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }
    }

    public void printSandwiches(Shop shop) {
        for (SandwichType sandwich: this.getSandwichesFromShop(shop))
            sandwich.printContents();
    }

}
