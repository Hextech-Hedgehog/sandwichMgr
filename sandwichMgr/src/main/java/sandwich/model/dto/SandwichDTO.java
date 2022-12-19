package sandwich.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "sandwich")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SandwichDTO {

    private int sandwichId;
    @JsonProperty(value = "sandwichtype")
    private SandwichTypeDTO sandwichType;
    private boolean asClub;
    private boolean withButter;
    private String optionalRequirement;
    @JsonProperty(value = "user")
    private UserDTO user;

    public SandwichDTO(){}

    public SandwichDTO(SandwichTypeDTO sandwichType, boolean asClub, boolean withButter, String optionalRequirement, UserDTO user) {
        this.sandwichType = sandwichType;
        this.asClub = asClub;
        this.withButter = withButter;
        this.optionalRequirement = optionalRequirement;
        this.user = user;
    }

    public SandwichDTO(int sandwichId, SandwichTypeDTO sandwichType, boolean asClub, boolean withButter, String optionalRequirement, UserDTO user) {
        this(sandwichType, asClub, withButter, optionalRequirement, user);
        this.sandwichId = sandwichId;
    }

    public int getSandwichId() {
        return this.sandwichId;
    }

    public void setSandwichId(int orderId) {
        this.sandwichId = orderId;
    }

    public SandwichTypeDTO getSandwichType() {
        return sandwichType;
    }

    public void setSandwichType(SandwichTypeDTO sandwichType) {
        this.sandwichType = sandwichType;
    }

    public boolean isAsClub() {
        return asClub;
    }

    public void setAsClub(boolean asClub) {
        this.asClub = asClub;
    }

    public boolean isWithButter() {
        return withButter;
    }

    public void setWithButter(boolean withButter) {
        this.withButter = withButter;
    }

    public String getOptionalRequirement() {
        return optionalRequirement;
    }

    public void setOptionalRequirement(String optionalRequirement) {
        this.optionalRequirement = optionalRequirement;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
