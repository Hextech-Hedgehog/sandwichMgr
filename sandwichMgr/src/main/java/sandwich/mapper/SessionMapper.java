package sandwich.mapper;

import org.springframework.stereotype.Component;
import sandwich.model.dto.SessionDTO;
import sandwich.model.entities.Session;

@Component
public class SessionMapper {
    public static SessionDTO toDto(Session session) {
        return new SessionDTO(session.getSessionId(), session.getSessionName(), UserMapper.toDto(session.getInstructor()), session.getStartDate(), session.getEndDate(), OrderMapper.toDto(session.getDailyOrder()));
    }

    public static Session toSession(SessionDTO session) {
        return new Session(session.getSessionName(), UserMapper.toUser(session.getInstructor()), session.getStartDate(), session.getEndDate(), OrderMapper.toOrder(session.getDailyOrder()));
    }
}
