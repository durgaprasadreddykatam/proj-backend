package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.Question;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class QuestionSer implements IQuestionService{

    @Autowired
    QuestionRepo questionRepo;
    @Override
    public UUID addQuestion(Question question) {
        questionRepo.save(question);
        return question.getQuestionId();
    }

    @Override
    public String deleteQuestion(UUID questionID) throws EtAuthException {
        try{
            int count = questionRepo.getCountById(questionID);
            if(count==0) throw new EtAuthException("Failed to Delete");
            questionRepo.deleteById(questionID);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return "Sucessful";
    }

    @Override
    public Question fetchQuestion(UUID questionID) {
        Optional<Question> question=questionRepo.findById(questionID);
        if(question.isPresent()) return question.get();
        else throw new EtAuthException("Invalid QuestionId");
    }

    @Override
    public UUID updateQuestion(Question question) {
        Optional<Question> optional=questionRepo.findById(question.getQuestionId());
        if(optional.isPresent()){
            questionRepo.save(question);
            return question.getQuestionId();
        }
        else throw new EtAuthException("Invalid QuestionId");
    }

    @Override
    public List<Question> fetchQuestions(String type, int count) {
        List<Question> questions=questionRepo.fetchByCountAndType(type, PageRequest.of(0, count));
        return questions;
    }

    @Override
    public String addQuestions(List<Question> questions) {
        questionRepo.saveAll(questions);
        return "Successfly saved all questions";
    }
}
