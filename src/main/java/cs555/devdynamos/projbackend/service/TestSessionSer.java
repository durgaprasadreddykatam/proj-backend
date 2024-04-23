package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.TestSession;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.repositories.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class TestSessionSer implements ITestSessionService{

    @Autowired
    SessionRepo sessionRepo;
    @Override
    public UUID createNewSession(TestSession session) {
        sessionRepo.save(session);
        return session.getSessionId();
    }

    @Override
    public void updateSessionEndTime(UUID sessionId, Timestamp sessionEndTime) {
        Optional<TestSession> optional=sessionRepo.findById(sessionId);
        if(optional.isPresent()){
            TestSession session= optional.get();
            session.setSessionEndTimeStamp(sessionEndTime);
            sessionRepo.save(session);
        }else throw new EtAuthException("Invalid SessionId");

    }
}
