package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.domain.UserResponse;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserResponseRepository implements IUserResponseRepository{

    private static String SQL_INSERT="INSERT INTO userresponses (userResponseId,sessionId,USER_ID," +
            "questionId,userAnswer,userrole,questionstarttimestamp,questionendtimestamp) VALUES (?,?,?,?,?,?,?,?)";
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public UUID addUserResponse(UserResponse userResponse) {
        try {
            jdbcTemplate.update(SQL_INSERT, userResponse.userResponseID(),
                    userResponse.sessionID(), userResponse.userID(),
                    userResponse.questionID(), userResponse.UserAnswer(),
                    userResponse.role(), userResponse.questionStartTimeStamp(),
                    userResponse.questionEndTimeStamp());
            return userResponse.userResponseID();
        } catch (Exception e){
            throw new EtAuthException("Failed to Insert UserResponse");
        }
    }
}
