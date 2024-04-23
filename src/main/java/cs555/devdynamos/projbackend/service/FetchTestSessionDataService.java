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
        List<TestSession> sessionList=sessionRepo.findAllByUserIdIsOrderBySessionStartTimeStamp(userId);

        List<Map<String,Object>> sessionDataList = new ArrayList<>();
        for (TestSession session : sessionList) {
            Map<String,Object> hashMap=new HashMap<>();
            UUID sessionId = session.getSessionId();
            Timestamp startTimestamp=session.getSessionStartTimeStamp();
            Timestamp endTimestamp=session.getSessionEndTimeStamp();
            LocalDate sessionDate = startTimestamp.toLocalDateTime().toLocalDate();
            LocalTime sessionStartTime = startTimestamp.toLocalDateTime().toLocalTime();
            if(endTimestamp !=null){
            LocalTime sessionEndTime = endTimestamp.toLocalDateTime().toLocalTime();
                hashMap.put("sessionEndTime",sessionEndTime);
            }
            else {
               String sessionEndTime=null;
                hashMap.put("sessionEndTime",sessionEndTime);
            }


            hashMap.put("sessionId",session.getSessionId());
            hashMap.put("sessiondate",sessionDate);
            hashMap.put("sessionStartTime",sessionStartTime);
            hashMap.put("sessionStartTime",sessionStartTime);

            List<UserResponse> userDataList = userResponseRepo.getAllBySessionIDOrderByQuestionStartTimeStamp(sessionId);
            hashMap.put("userResponses",userDataList);
            sessionDataList.add(hashMap);
        }

        return sessionDataList;

    }
}
