package model;

import java.time.LocalDate;
import java.time.Period;

public class Session {

    private String sessionName;
    private StaffMember instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    private Order dailyOrder;

    public Session(String sessionName, StaffMember instructor, LocalDate startDate, LocalDate endDate) throws IllegalArgumentException {
        this.sessionName = sessionName;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
        if (!startDate.isBefore(endDate))
            throw new IllegalArgumentException("startDate should be < than endDate");
    }

    public void printInfo() {
        System.out.println(sessionName + " with instructor " + instructor.getFirstName());
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

    public StaffMember getInstructor() {
        return instructor;
    }

    public void setInstructor(StaffMember instructor) {
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
