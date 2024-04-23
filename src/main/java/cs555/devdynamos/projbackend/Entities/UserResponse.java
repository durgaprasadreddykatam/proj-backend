package cs555.devdynamos.projbackend.domain;

import java.sql.Timestamp;
import java.util.UUID;

public record UserResponse(UUID userResponseID, UUID sessionID, String userID, UUID questionID,
                           String UserAnswer, String role, Timestamp questionStartTimeStamp,
                           Timestamp questionEndTimeStamp) {
}
