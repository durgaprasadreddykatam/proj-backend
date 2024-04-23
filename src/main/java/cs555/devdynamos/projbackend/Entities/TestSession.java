package cs555.devdynamos.projbackend.Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class TestSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="session_id")
    UUID sessionId;
    @Column(name="user_id")
    UUID userId;
    Timestamp sessionStartTimeStamp;
    Timestamp sessionEndTimeStamp;

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Timestamp getSessionStartTimeStamp() {
        return sessionStartTimeStamp;
    }

    public void setSessionStartTimeStamp(Timestamp sessionStartTimeStamp) {
        this.sessionStartTimeStamp = sessionStartTimeStamp;
    }

    public Timestamp getSessionEndTimeStamp() {
        return sessionEndTimeStamp;
    }

    public void setSessionEndTimeStamp(Timestamp sessionEndTimeStamp) {
        this.sessionEndTimeStamp = sessionEndTimeStamp;
    }
}
