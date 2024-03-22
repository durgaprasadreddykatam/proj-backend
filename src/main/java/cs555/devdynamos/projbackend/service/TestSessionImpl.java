package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.domain.TestSession;
import cs555.devdynamos.projbackend.repositories.ITestSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class TestSessionImpl implements ITestSessionService {

    @Autowired
    ITestSessionRepository testSessionRepository;
    @Override
    public UUID createNewSession(TestSession session) {
        return testSessionRepository.generateSessionId(session);
    }

    @Override
    public void updateSessionEndTime(UUID sessionId, Timestamp sessionEndTime) {
        testSessionRepository.updateSession(sessionId,sessionEndTime);
    }
}
