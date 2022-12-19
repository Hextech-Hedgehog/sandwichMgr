package sandwich.model.entities;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name="sessions")
public class Session {

    @Id
    @SequenceGenerator(name="sessionGen", sequenceName = "session_seid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessionGen")
    @Column(name="seid")
    private int sessionId;
    @Column(name="sename")
    private String sessionName;
    @OneToOne
    @JoinColumn(name="se_uid")
    private User instructor;
    @Column(name="sestdate")
    private LocalDate startDate;
    @Column(name="seedate")
    private LocalDate endDate;
    @ManyToOne(cascade = {MERGE, PERSIST})
    @JoinColumn(name="se_orid")
    private Order dailyOrder;

    public Session(){}

    public Session(String sessionName, User instructor, LocalDate startDate, LocalDate endDate, Order order) throws IllegalArgumentException {
        this.sessionName = sessionName;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyOrder = order;
        if (!startDate.isBefore(endDate))
            throw new IllegalArgumentException("startDate should be < than endDate");
    }

    public void printInfo() {
        System.out.println(sessionName + " with instructor " + instructor.getFirstName());
    }

    public int getSessionId() {
        return sessionId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public boolean coversDate(LocalDate date) {
        return (this.getStartDate().isBefore(date) || this.getStartDate().isEqual(date)) &&
            (this.getEndDate().isAfter(date) || this.getEndDate().isEqual(date));
    }

    public Order getDailyOrder() {
        if (dailyOrder == null || !dailyOrder.getDate().isEqual(LocalDate.now()))
            dailyOrder = new Order();
        return dailyOrder;
    }
}
