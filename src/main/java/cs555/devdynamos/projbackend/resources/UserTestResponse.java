package cs555.devdynamos.projbackend.resources;

import cs555.devdynamos.projbackend.Entities.Question;
import cs555.devdynamos.projbackend.Entities.TestSession;
import cs555.devdynamos.projbackend.Entities.UserResponse;
import cs555.devdynamos.projbackend.service.IQuestionService;
import cs555.devdynamos.projbackend.service.IResponseService;
import cs555.devdynamos.projbackend.service.ITestSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/UserTestResponse")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserTestResponse {
    @Autowired
    IQuestionService questionService;
    @Autowired
    ITestSessionService sessionService;

    @Autowired
    IResponseService responseService;
    @PostMapping("/generateSession")
    public ResponseEntity<Map<String,Object>> generateSession(@RequestBody Map<String,Object> responseMap){
        String userId=(String)responseMap.get("userId");
        Timestamp startTimeStamp =Timestamp.valueOf((String)responseMap.get("startTimeStamp"));
        String type=(String)responseMap.get("type");
        List<Question> questions = questionService.fetchQuestions(type,(Integer)responseMap.get("count"));
        TestSession testSession=new TestSession();
        testSession.setUserId(UUID.fromString(userId));
        testSession.setSessionStartTimeStamp(startTimeStamp);
        UUID sessionId = sessionService.createNewSession(testSession);
        Map<String, Object> response = new HashMap<>();
        response.put("sessionId", sessionId);
        response.put("questions", questions);

        return new ResponseEntity<>(response, HttpStatus.OK);


    }
    @PostMapping("/saveUserResponse")
    public ResponseEntity<String> addUserResponses(@RequestBody Map<String,Object> responseMap){
        List<Map<String, Object>> questions = (List<Map<String, Object>>) responseMap.get("questions");
        UUID sessionId=UUID.fromString((String)responseMap.get("sessionId"));
        Timestamp sessionEndTime =Timestamp.valueOf((String)responseMap.get("sessionEndTime"));
        String userId=(String)responseMap.get("userId");
        sessionService.updateSessionEndTime(sessionId,sessionEndTime);
        for(Map<String, Object> questionMap :questions){
            UserResponse userResponse=new UserResponse();
            userResponse.setSessionID(sessionId);
            userResponse.setUserID(UUID.fromString(userId));
            userResponse.setQuestionID(UUID.fromString((String) questionMap.get("questionId")));
            userResponse.setUserAnswer((String) questionMap.get("userAnswer"));
            userResponse.setRole((String) questionMap.get("userRole"));
            userResponse.setQuestionStartTimeStamp(Timestamp.valueOf((String) questionMap.get("questionStartTime")));
            userResponse.setQuestionEndTimeStamp(Timestamp.valueOf((String) questionMap.get("questionEndTime")));

            responseService.addUserResponse(userResponse);

        }
        return new ResponseEntity<>("Succesfully added User Responses", HttpStatus.OK);
    }
}
