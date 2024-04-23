package cs555.devdynamos.projbackend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;



@Entity(name="questions")
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID questionId;
    String question;
    String answer;
    String questionType;

    public Question (){

    }
    public Question(UUID questionId, String question, String answer, String questionType) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.questionType = questionType;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
