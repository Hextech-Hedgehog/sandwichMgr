package sandwich.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.LocalDate;

@JsonRootName(value = "session")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDTO {

    private int sessionId;
    private String sessionName;
    @JsonProperty(value = "instructor")
    private UserDTO instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    @JsonProperty(value = "order")
    private OrderDTO dailyOrder;

    public SessionDTO() {}

    public SessionDTO(String sessionName, UserDTO instructor, LocalDate startDate, LocalDate endDate, OrderDTO dailyOrder) {
        this.sessionName = sessionName;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyOrder = dailyOrder;
    }

    public SessionDTO(int sessionId, String sessionName, UserDTO instructor, LocalDate startDate, LocalDate endDate, OrderDTO dailyOrder) {
        this(sessionName, instructor, startDate, endDate, dailyOrder);
        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public UserDTO getInstructor() {
        return instructor;
    }

    public void setInstructor(UserDTO instructor) {
        this.instructor = instructor;
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

    public OrderDTO getDailyOrder() {
        return dailyOrder;
    }

    public void setDailyOrder(OrderDTO dailyOrder) {
        this.dailyOrder = dailyOrder;
    }
}
