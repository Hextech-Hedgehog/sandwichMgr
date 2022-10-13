package model;

import java.time.LocalDate;
import java.time.Period;

public class Session {

    private String sessionName;
    private StaffMember instructor;
    private LocalDate startDate;
    private LocalDate endDate;

    public Session(String sessionName, StaffMember instructor, Period period) {
        this.sessionName = sessionName;
        this.instructor = instructor;
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
}
