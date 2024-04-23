package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.TestSession;

import java.sql.Timestamp;
import java.util.UUID;

public interface ITestSessionService {
    public UUID createNewSession(TestSession session);

    public void updateSessionEndTime(UUID sessionId, Timestamp sessionEndTime);
}
