package cs555.devdynamos.projbackend.Entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_response_id")
    UUID userResponseID;
    @Column(name="session_id")
    UUID sessionID;
    @Column(name="user_id")
    UUID userID;
    @Column(name="question_id")
    UUID questionID;

    String UserAnswer;
    @Column(name="user_role")
    String role;
    Timestamp questionStartTimeStamp;
    Timestamp questionEndTimeStamp;

    public UUID getUserResponseID() {
        return userResponseID;
    }

    public void setUserResponseID(UUID userResponseID) {
        this.userResponseID = userResponseID;
    }

    public UUID getSessionID() {
        return sessionID;
    }

    public void setSessionID(UUID sessionID) {
        this.sessionID = sessionID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UUID getQuestionID() {
        return questionID;
    }

    public void setQuestionID(UUID questionID) {
        this.questionID = questionID;
    }

    public String getUserAnswer() {
        return UserAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        UserAnswer = userAnswer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getQuestionStartTimeStamp() {
        return questionStartTimeStamp;
    }

    public void setQuestionStartTimeStamp(Timestamp questionStartTimeStamp) {
        this.questionStartTimeStamp = questionStartTimeStamp;
    }

    public Timestamp getQuestionEndTimeStamp() {
        return questionEndTimeStamp;
    }

    public void setQuestionEndTimeStamp(Timestamp questionEndTimeStamp) {
        this.questionEndTimeStamp = questionEndTimeStamp;
    }

}