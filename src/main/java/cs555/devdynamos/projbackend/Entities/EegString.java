package cs555.devdynamos.projbackend.Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "eeg_string")
public class EegString {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sequence_id")
    UUID sequenceId;


    @Column(name = "session_id")
    UUID SessionId;


    @Column(name = "eeg_data")
    String EegJsonString;

    @Column(name = "train_or_test")
    String trainData;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name="role")
    String role;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    Timestamp timestamp;

    public UUID getSessionId() {
        return SessionId;
    }

    public void setSessionId(UUID sessionId) {
        SessionId = sessionId;
    }




    public String getEegJsonString() {
        return EegJsonString;
    }

    public void setEegJsonString(String eegJsonString) {
        EegJsonString = eegJsonString;
    }

    public String getTrainData() {
        return trainData;
    }

    public void setTrainData(String trainData) {
        this.trainData = trainData;
    }
    public UUID getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(UUID sequenceId) {
        this.sequenceId = sequenceId;
    }
}
