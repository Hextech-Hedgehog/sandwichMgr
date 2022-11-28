package sandwich.model;

public enum Shop { //TODO change from enum to normal class
    PINKYS("sandwichTypePinkys.txt"),
    VLEUGELS("sandwichTypeVleugels.txt");

    private final String pathWay; //TODO remove

    Shop(String pathWay) {
        this.pathWay = pathWay;
    }


    public String getPathWay() {
        return pathWay;
    }
}


