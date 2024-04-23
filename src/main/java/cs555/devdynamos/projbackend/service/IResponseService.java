package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.UserResponse;

import java.util.UUID;

public interface IResponseService {
    public UUID addUserResponse(UserResponse userResponse);
}
