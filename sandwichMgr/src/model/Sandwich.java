package model;

import java.sql.SQLOutput;

public class Sandwich {

    private SandwichType sandwichType;
    private boolean asClub;
    private boolean withButter;
    private String optionalRequirement;

    public Sandwich(SandwichType sandwichType, boolean asClub, boolean withButter) {
        this.sandwichType = sandwichType;
        this.asClub = asClub;
        this.withButter = withButter;
    }

    public Sandwich(SandwichType sandwichType, boolean asClub, boolean withButter, String optionalRequirement) {
        this(sandwichType, asClub, withButter);
        this.optionalRequirement = optionalRequirement;
    }

    public void printContents() {
        System.out.print(this.sandwichType.name());
        if (this.asClub)
            System.out.print(" as club");
        if (this.withButter)
            System.out.print(" with extra butter");
        System.out.println(":");
        this.sandwichType.printInfo();
    }

    @Override
    public int hashCode() {
        int hash = this.sandwichType.name().hashCode();
        byte boolByteValue = 0;
        boolByteValue += asClub ? 1 : 0;
        boolByteValue += withButter ? 2 : 0;
        hash += boolByteValue ;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sandwich) {
            Sandwich s = (Sandwich)obj;
            return s.sandwichType == this.sandwichType && s.asClub == this.asClub && s.withButter == this.withButter;
        }
        return false;
    }

}
