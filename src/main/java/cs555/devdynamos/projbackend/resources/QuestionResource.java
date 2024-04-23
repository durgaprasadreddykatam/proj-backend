package cs555.devdynamos.projbackend.resources;

import cs555.devdynamos.projbackend.Entities.Question;
import cs555.devdynamos.projbackend.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionResource {
    @Autowired
    IQuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Map<String,Object> questionMap){
        Question question = new Question();
        question.setQuestion((String) questionMap.get("question"));
        question.setAnswer((String) questionMap.get("answer"));
        question.setQuestionType((String) questionMap.get("questionType"));
        UUID questionId = questionService.addQuestion(question);
        return new ResponseEntity<>("Question added successfully with ID: " + questionId, HttpStatus.OK);
    }


    @PostMapping("/delete")
    public ResponseEntity<String> deleteQuestion(@RequestBody Map<String,Object> questionMap){
         questionService.deleteQuestion(UUID.fromString((String) questionMap.get("questionId")));
        return new ResponseEntity<>("Question deleted successfully with ID: " , HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<String> updateQuestion(@RequestBody Map<String,Object> questionMap){
        Question question=new Question();
        question.setQuestionId(UUID.fromString((String) questionMap.get("questionId")));
        question.setQuestion((String) questionMap.get("question"));
        question.setAnswer((String) questionMap.get("answer"));
        question.setQuestionType((String) questionMap.get("questionType"));
        UUID updatedQuestionId = questionService.updateQuestion(question);
        return new ResponseEntity<>("Question Updated successfully with ID: " + updatedQuestionId, HttpStatus.OK);}
    @PostMapping("/addQuestions")
    public ResponseEntity<String> addQuestions(@RequestBody List<Question> questions) {
        String message = questionService.addQuestions(questions);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/fetchQuestion")
    public ResponseEntity<Question> fetchQuestion(@RequestBody Map<String,Object> questionMap) {
        Question question = questionService.fetchQuestion(UUID.fromString((String) questionMap.get("questionId")));
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
    @GetMapping("/fetchQuestionList")
    public ResponseEntity<List<Question>> fetchQuestions(@RequestBody Map<String,Object> questionMap) {
        List<Question> questions = questionService.fetchQuestions((String)questionMap.get("type"),(Integer)questionMap.get("count"));
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
