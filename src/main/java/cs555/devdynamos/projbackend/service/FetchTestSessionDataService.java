package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.TestSession;
import cs555.devdynamos.projbackend.Entities.UserResponse;
import cs555.devdynamos.projbackend.repositories.SessionRepo;
import cs555.devdynamos.projbackend.repositories.UserResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional
public class FetchTestSessionDataService {

    @Autowired
    SessionRepo sessionRepo;
    @Autowired
    UserResponseRepo userResponseRepo;

    public List<Map<String,Object>> fetchUserSessionData(UUID userId){
        List<TestSession> sessionList=sessionRepo.getByUserId(userId);

        List<Map<String,Object>> sessionDataList = new ArrayList<>();
        for (TestSession session : sessionList) {
            Map<String,Object> hashMap=new HashMap<>();
            UUID sessionId = session.getSessionId();
//            LocalDate sessionDate = startTimestamp.toLocalDateTime().toLocalDate();
//            LocalTime sessionStartTime = startTimestamp.toLocalDateTime().toLocalTime();


            hashMap.put("sessionId",session.getSessionId());
            hashMap.put("sessiondate",session.getSessionStartTimeStamp());
            hashMap.put("sessionStartTime",session.getSessionStartTimeStamp());
            hashMap.put("reslut",session.getResult());
            List<UserResponse> userDataList = userResponseRepo.getAllBySessionIDOrderByQuestionStartTimeStamp(sessionId);
            hashMap.put("userResponses",userDataList);
            sessionDataList.add(hashMap);
        }
        System.out.println(sessionDataList);
        return sessionDataList;

    }
}
