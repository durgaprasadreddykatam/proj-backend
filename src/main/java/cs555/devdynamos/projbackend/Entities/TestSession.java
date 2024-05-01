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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTestOrTrain() {
        return testOrTrain;
    }

    public void setTestOrTrain(String testOrTrain) {
        this.testOrTrain = testOrTrain;
    }

    @Column(name="role")
    String role;
    @Column(name="test_or_train")
    String testOrTrain;
    @Column(name = "result")
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

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
