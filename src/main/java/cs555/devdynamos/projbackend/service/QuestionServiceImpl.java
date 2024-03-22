package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.domain.Question;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.repositories.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class QuestionServiceImpl implements IQuestionService{
    @Autowired
    IQuestionRepository questionRepository;
    @Override
    public UUID addQuestion(Question question) {
        if (question.questionType().equals("Arithmetic") || question.questionType().equals("Image") || question.questionType().equals("Random")) {
            return questionRepository.addQuestion(question);
        }
        else{
            throw new EtAuthException("Invalid Question Type");
        }
    }

    @Override
    public String deleteQuestion(String questionID) {
        UUID questionId = UUID.fromString(questionID);
        questionRepository.deleteQuestion(questionId);
        return null;
    }

    @Override
    public Question fetchQuestion(String questionID) {
        UUID questionId=UUID.fromString(questionID);
        return questionRepository.fetchQuestion(questionId);
    }

    @Override
    public UUID updateQuestion(Question question) {
        if (question.questionType().equals("Arithmetic") || question.questionType().equals("Image") || question.questionType().equals("Random")) {
            return questionRepository.updateQuestion(question);
        }
        else{
            throw new EtAuthException("Invalid Question Type Provided for Updation");
        }
    }

    @Override
    public List<Question> fetchQuestions(String type, int count) {
        return questionRepository.fetchQuestions(type,count);
    }

    @Override
    public String addQuestions(List<Question> questions) {
        return questionRepository.addQuestions(questions);
    }
}
