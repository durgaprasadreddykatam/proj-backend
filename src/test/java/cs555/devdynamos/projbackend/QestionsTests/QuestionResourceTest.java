package cs555.devdynamos.projbackend.QestionsTests;


import cs555.devdynamos.projbackend.Entities.Question;
import cs555.devdynamos.projbackend.resources.QuestionResource;
import cs555.devdynamos.projbackend.service.IQuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionResourceTest {

    @Mock
    private IQuestionService questionService;

    @InjectMocks
    private QuestionResource questionResource;

    @Test
    public void testAddQuestion() {
        UUID id=UUID.randomUUID();
        Question question = new Question(id, "Sample question","Sample answer", "Random");
        Map<String, Object> questionMap = new HashMap<>();
        questionMap.put("question", question.question());
        questionMap.put("answer", question.answer());
        questionMap.put("questionType", question.questionType());
        when(questionService.addQuestion(any(Question.class))).thenReturn(id);
        ResponseEntity<String> responseEntity = questionResource.addQuestion(questionMap);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Question added successfully with ID: " + id, responseEntity.getBody());

    }


}
