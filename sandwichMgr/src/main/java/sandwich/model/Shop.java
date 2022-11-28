package sandwich.model;

public enum Shop {
    PINKYS("sandwichTypePinkys.txt"),
    VLEUGELS("sandwichTypeVleugels.txt");               // TODO refactor

    private final String pathWay;

    Shop(String pathWay) {
        this.pathWay = pathWay;
    }


    public String getPathWay() {
        return pathWay;
    }
}


