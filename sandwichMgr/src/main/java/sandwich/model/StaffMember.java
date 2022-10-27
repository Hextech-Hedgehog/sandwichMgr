package sandwich.model;

import org.apache.logging.log4j.LogManager;
import sandwich.exception.SessionNotFoundException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class StaffMember extends Person {

    private Set<Session> sessions = new HashSet<>();

    public StaffMember(String firstName) {
        super(firstName);
        this.setSandwichPayedByAbis(false);
    }

    public Session getSessionByDate(LocalDate date) {
        Session session = this.sessions.stream().filter(e -> e.coversDate(date)).findFirst().orElse(null);
        if (session == null)
            LogManager.getLogger("error").error("No session found at date: " + date);
        return session;
    }

    public void teachSession(Session session) {
        this.sessions.add(session);
    }

}
