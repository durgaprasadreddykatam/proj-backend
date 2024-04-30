package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.User;
import cs555.devdynamos.projbackend.Entities.UserResponse;
import cs555.devdynamos.projbackend.repositories.UserRepoJpa;
import cs555.devdynamos.projbackend.repositories.UserResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
public class ResponseService implements IResponseService{
    @Autowired
    UserResponseRepo userResponseRepo;
    @Autowired
    UserRepoJpa userRepo;
    @Override
    public UUID addUserResponse(UserResponse userResponse) {
        userResponseRepo.save(userResponse);
        return userResponse.getUserResponseID();
    }
    @Override
    public User updateUserActivity(UUID userId, String role, String trainOrTest) {
        if ("Train".equals(trainOrTest)) {
            User user = userRepo.findById(userId).orElse(null);
            if ("Liar".equals(role)) {
                user.setIntroTestTakenAsLiar(true);
            } else {
                user.setIntroTestTakenAsTruthTeller(true);
            }
            userRepo.save(user);
            return user;
        }
        return null;
    }
}
