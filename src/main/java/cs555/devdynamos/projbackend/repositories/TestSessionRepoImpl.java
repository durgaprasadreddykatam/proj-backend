package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.domain.TestSession;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

@Repository
public class TestSessionRepoImpl implements ITestSessionRepository{
    public static final String  SQL_UPDATE ="UPDATE testsessions SET sessionendtimestamp = ? WHERE SessionID=?";
    public static final String  SQL_GENERATE ="INSERT INTO testsessions(SessionID,USER_ID,SessionStartTimeStamp) VALUES (?,?,?)";
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public UUID generateSessionId(TestSession sessionData) {
        try{
            jdbcTemplate.update(SQL_GENERATE,sessionData.sessionID(),sessionData.userID(),sessionData.sessionStartTimeStamp());
            return sessionData.sessionID();
        }
        catch (Exception e) {
            throw new EtAuthException("Failed to Insert session data in DB");
        }
    }

    @Override
    public void updateSession(UUID sessionId, Timestamp SessionEndTime) {
        try{
            jdbcTemplate.update(SQL_UPDATE,SessionEndTime,sessionId);
        }
        catch (Exception e) {
            throw new EtAuthException("Failed to Update Seesion End Time");
        }

    }
}
