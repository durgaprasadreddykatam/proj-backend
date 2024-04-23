package cs555.devdynamos.projbackend.domain;

import java.sql.Timestamp;
import java.util.UUID;

public record TestSession(UUID sessionID, String userID, Timestamp sessionStartTimeStamp,
                          Timestamp sessionEndTimeStamp) {
    public TestSession(UUID sessionID, String userID, Timestamp sessionStartTimeStamp) {
        this(sessionID, userID, sessionStartTimeStamp, null);
    }
}
