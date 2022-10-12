package repository;

import model.SandwichType;

import java.util.List;

public interface SandwichTypeRepo {

    void writeToRepo(List<SandwichType> sandwichTypes);
    List<SandwichType> readFromRepo();
}
