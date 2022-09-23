package model;

public class Session {

    private String sessionName;
    private StaffMember instructor;

    public Session(String sessionName, StaffMember instructor) {
        this.sessionName = sessionName;
        this.instructor = instructor;
    }

    public void printInfo() {
        System.out.println(sessionName + " with instructor " + instructor.getFirstName());
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
