package sandwich.factory;

import sandwich.model.entities.Sandwich;
import sandwich.model.entities.SandwichType;

public class SandwichFactory {

    private static SandwichFactory sandwichFactory = new SandwichFactory();
    private Sandwich sandwich;

    private SandwichFactory(){}

    public static SandwichFactory getSandwichFactory() {
        return sandwichFactory;
    }

    public SandwichFactory makeSandwich(SandwichType sandwichType) {
        this.sandwich = new Sandwich(sandwichType);
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
