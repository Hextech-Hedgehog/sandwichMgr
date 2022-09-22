package model;

public class Sandwich {

    private SandwichType sandwichType;
    private boolean asClub;
    private boolean withButter;
    private String optionalRequirement;

    public Sandwich(SandwichType sandwichType, boolean asClub, boolean withButter, String optionalRequirement) {
        this.sandwichType = sandwichType;
        this.asClub = asClub;
        this.withButter = withButter;
        this.optionalRequirement = optionalRequirement;
    }

    public void printContents() {

    }

}
