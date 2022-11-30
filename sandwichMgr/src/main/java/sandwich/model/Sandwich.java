package sandwich.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name="sandwich")
public class Sandwich {

    @Id
    @SequenceGenerator(name="sandwichGen", sequenceName = "sandwich_sid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sandwichGen")
    @Column(name="sid")
    private int sandwichId;
    @ManyToOne(cascade = {MERGE, PERSIST})
    @JoinColumn(name = "s_stid")
    private SandwichType sandwichType;
    @Column(name="club")
    private boolean asClub;
    @Column(name="butter")
    private boolean withButter;
    @Column(name="optional")
    private String optionalRequirement;
    @OneToOne
    @JoinColumn(name="s_uid")
    private User user;

    public Sandwich() {}

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

    public Sandwich(SandwichType sandwichType, boolean asClub, boolean withButter, String optionalRequirement, User user) {
        this.sandwichId = sandwichId;
        this.sandwichType = sandwichType;
        this.asClub = asClub;
        this.withButter = withButter;
        this.optionalRequirement = optionalRequirement;
        this.user = user;
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

    public int getSandwichId() {
        return sandwichId;
    }

    public void setAsClub(boolean asClub) {
        this.asClub = asClub;
    }

    public void setWithButter(boolean withButter) {
        this.withButter = withButter;
    }

    public void setSandwichType(SandwichType sandwichType) {
        this.sandwichType = sandwichType;
    }

    public SandwichType getSandwichType() {
        return sandwichType;
    }

    public String getOptionalRequirement() {
        return optionalRequirement;
    }

    public void setOptionalRequirement(String optionalRequirement) {
        this.optionalRequirement = optionalRequirement;
    }

    public boolean isAsClub() {
        return asClub;
    }

    public boolean isWithButter() {
        return withButter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
