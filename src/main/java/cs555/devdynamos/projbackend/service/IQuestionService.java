package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.Question;

import java.util.List;
import java.util.UUID;

public interface IQuestionService {
    public UUID addQuestion(Question question);

    public String deleteQuestion(UUID questionID);

    public Question fetchQuestion(UUID questionID);

    public UUID updateQuestion(Question question);

    public List<Question> fetchQuestions(String type, int count);
    String addQuestions(List<Question> questions);
}
