package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.domain.UserResponse;

import java.util.UUID;

public interface IUserResponseRepository {
    public UUID addUserResponse(UserResponse userResponse);
}
