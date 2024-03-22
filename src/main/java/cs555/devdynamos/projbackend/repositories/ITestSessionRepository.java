package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.domain.TestSession;

import java.sql.Timestamp;
import java.util.UUID;

public interface ITestSessionRepository {
    public UUID generateSessionId(TestSession sessionData);

    public void updateSession(UUID sessionId, Timestamp SessionEndTime);
}
