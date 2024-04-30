package cs555.devdynamos.projbackend.resources;

import cs555.devdynamos.projbackend.Constants;
import cs555.devdynamos.projbackend.Entities.Question;
import cs555.devdynamos.projbackend.Entities.TestSession;
import cs555.devdynamos.projbackend.Entities.User;
import cs555.devdynamos.projbackend.Entities.UserResponse;
import cs555.devdynamos.projbackend.repositories.UserRepoJpa;
import cs555.devdynamos.projbackend.service.IQuestionService;
import cs555.devdynamos.projbackend.service.IResponseService;
import cs555.devdynamos.projbackend.service.ITestSessionService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

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

    @Autowired
    UserRepoJpa userRepoJpa;
    @PostMapping("/generateSession")
    public ResponseEntity<Map<String,Object>> generateSession(@RequestBody Map<String,Object> responseMap){
        String userId=(String)responseMap.get("userId");
        Timestamp startTimeStamp =Timestamp.valueOf((String)responseMap.get("startTimeStamp"));
        String type=(String)responseMap.get("type");
        String role=(String)responseMap.get("role");
        String trainOrTest=(String)responseMap.get("trainOrTest");
        List<Question> questions = questionService.fetchQuestions(type,(Integer)responseMap.get("count"));
        TestSession testSession=new TestSession();
        testSession.setUserId(UUID.fromString(userId));
        testSession.setSessionStartTimeStamp(startTimeStamp);
        testSession.setTestOrTrain(trainOrTest);
        testSession.setRole(role);
        UUID sessionId = sessionService.createNewSession(testSession);
        Map<String, Object> response = new HashMap<>();
        response.put("sessionId", sessionId);
        response.put("questions", questions);

        return new ResponseEntity<>(response, HttpStatus.OK);


    }
    @PostMapping("/saveUserResponse")
    public ResponseEntity<Map<String,String>> addUserResponses(@RequestBody Map<String,Object> responseMap) {
        List<Map<String, Object>> questions = (List<Map<String, Object>>) responseMap.get("questions");
        UUID sessionId = UUID.fromString((String) responseMap.get("sessionId"));
        Timestamp sessionEndTime = Timestamp.valueOf((String) responseMap.get("sessionEndTime"));
        String userId = (String) responseMap.get("userId");
        String role = (String) responseMap.get("role");
        String trainOrTest = (String) responseMap.get("trainOrTest");
        User user = responseService.updateUserActivity(UUID.fromString(userId), role, trainOrTest);
        User user1 =userRepoJpa.findById(UUID.fromString(userId)).orElse(null);
        sessionService.updateSessionEndTime(sessionId, sessionEndTime);
        for (Map<String, Object> questionMap : questions) {
            UserResponse userResponse = new UserResponse();
            userResponse.setSessionID(sessionId);
            userResponse.setUserID(UUID.fromString(userId));
            userResponse.setQuestionID(UUID.fromString((String) questionMap.get("questionId")));
            userResponse.setUserAnswer((String) questionMap.get("userAnswer"));
            userResponse.setRole((String) questionMap.get("userRole"));
            userResponse.setQuestionStartTimeStamp(Timestamp.valueOf((String) questionMap.get("questionStartTime")));
            userResponse.setQuestionEndTimeStamp(Timestamp.valueOf((String) questionMap.get("questionEndTime")));
            responseService.addUserResponse(userResponse);

        }
            return new ResponseEntity<>(generateJwtToken(user,user1), HttpStatus.OK);
    }


    private Map<String,String> generateJwtToken(User user1,User user2){
        Map <String,String> map=new HashMap<>();
        if (user1 != null) {
            long timestamp=System.currentTimeMillis();
            String token= Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                    .setIssuedAt(new Date(timestamp))
                    .setExpiration(new Date(timestamp+Constants.TOKENVALIDITY))
                    .claim("userId",user1.getUserId())
                    .claim("email",user1.getEmail())
                    .claim("firstname",user1.getFirstName())
                    .claim("lastname",user1.getLastName())
                    .claim("introSeen",user1.isIntroSeen())
                    .claim("introTestTakenAsLiar",user1.isIntroTestTakenAsLiar())
                    .claim("introTestTakenAsTruthTeller",user1.isIntroTestTakenAsTruthTeller())
                    .claim("assignedNumber",user1.getAssignedNumber())
                    .compact();
            map.put("token",token);

        }
        else map.put("command", String.valueOf(user2.getAssignedNumber()));
        return map;


    }
}
