package factory;

import exception.SandwichNotFoundException;
import model.Sandwich;
import model.SandwichType;
import org.apache.logging.log4j.LogManager;

public class SandwichFactory {

    private static SandwichFactory sandwichFactory = new SandwichFactory();
    private Sandwich sandwich;

    private SandwichFactory(){}

    public static SandwichFactory getSandwichFactory() {
        return sandwichFactory;
    }

    public SandwichFactory makeSandwich(String sandwichName) {
        try {
            this.sandwich = new Sandwich(SandwichType.getSandwichTypeByName(sandwichName));
        } catch (SandwichNotFoundException e) {
            LogManager.getLogger("error").error(e.getMessage());
        }
        return this;
    }

    public SandwichFactory asClub(boolean asClub) {
        this.sandwich.setAsClub(asClub);
        return this;
    }

    public SandwichFactory withButter(boolean withButter) {
        this.sandwich.setWithButter(withButter);
        return this;
    }

    public Sandwich toSandwich() {
        return this.sandwich;
    }
}
