package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.domain.Question;

import java.util.List;
import java.util.UUID;

public interface IQuestionRepository {

    public UUID addQuestion(Question question);

    public void deleteQuestion(UUID questionID);

    public Question fetchQuestion(UUID questionID);

    public UUID updateQuestion(Question question);

    public List<Question> fetchQuestions(String type,int count);
    String addQuestions(List<Question> questions);

}
