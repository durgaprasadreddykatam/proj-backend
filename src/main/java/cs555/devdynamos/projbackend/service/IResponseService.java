package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.User;
import cs555.devdynamos.projbackend.Entities.UserResponse;

import java.util.UUID;

public interface IResponseService {
    public UUID addUserResponse(UserResponse userResponse);

    public User updateUserActivity(UUID userId, String role, String trainOrTest);
}
