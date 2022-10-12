package repository;

import model.SandwichType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SandwichTypeFileRepo implements SandwichTypeRepo {


    private final String pathWay =  "sandwichtype.txt";

    @Override
    public void writeToRepo(List<SandwichType> sandwichTypes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(pathWay))) {
            for (SandwichType type : sandwichTypes) {
                writer.write(type.name() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<SandwichType> readFromRepo() {
        List<SandwichType> sandwichTypes = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(pathWay))) {
            String sandwichType;
            while ((sandwichType = reader.readLine()) != null) {
                if (SandwichType.getSandwichTypeByName(sandwichType) != null) {         // TODO modify after exception creation
                    sandwichTypes.add(SandwichType.getSandwichTypeByName(sandwichType));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sandwichTypes;
    }
}
