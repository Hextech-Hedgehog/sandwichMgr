package sandwich.model;

public class Sandwich {

    private SandwichType sandwichType;
    private boolean asClub;
    private boolean withButter;
    private String optionalRequirement;
    //private user user;

    public Sandwich(SandwichType sandwichType) {
        this.sandwichType = sandwichType;
        this.asClub = false;
        this.withButter = false;
    }

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
        System.out.print(this.sandwichType.getSandwichName());
        if (this.asClub)
            System.out.print(" as club");
        if (this.withButter)
            System.out.print(" with extra butter");
        System.out.println(":");
        this.sandwichType.printContents();
    }

    @Override
    public int hashCode() {
        int hash = this.sandwichType.getSandwichName().hashCode();
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
            return s.sandwichType.equals(this.sandwichType) && s.asClub == this.asClub && s.withButter == this.withButter;
        }
        return false;
    }

    public void setAsClub(boolean asClub) {
        this.asClub = asClub;
    }

    public void setWithButter(boolean withButter) {
        this.withButter = withButter;
    }

    public SandwichType getSandwichType() {
        return sandwichType;
    }

    public String getOptionalRequirement() {
        return optionalRequirement;
    }

    public boolean isAsClub() {
        return asClub;
    }

    public boolean isWithButter() {
        return withButter;
    }
}
