package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.domain.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(User user) throws EtAuthException {
            if (user.getEmail() != null)
            user.setEmail(user.getEmail().toLowerCase());
            User userDet=userRepository.findByEmail(user);
            if(!BCrypt.checkpw(user.getPassword(),userDet.getPassword()))
                throw new EtAuthException("Invalid email/password");

        return userDet;
    }

    @Override
    public User registerUser(User user) throws EtAuthException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(7));
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (user.getEmail() != null)
            user.setEmail(user.getEmail().toLowerCase());
        if (!pattern.matcher(user.getEmail()).matches()) throw new EtAuthException("Invalid email format");
        Integer count = userRepository.getCountByEmail(user.getEmail());
        if (count > 0) throw new EtAuthException("Email already in use");
        user.setPassword(hashedPassword);
        user.setUserId(UUID.randomUUID());
        user.setIntroTestTaken(false);
        user.setIntroSeen(false);
        UUID userId = userRepository.create(user);
        return userRepository.findById(userId);
    }

    @Override
    public User updateUser(User user) throws EtAuthException {
        if (user.getEmail() != null)
            user.setEmail(user.getEmail().toLowerCase());
        Integer count = userRepository.getCountByEmail(user.getEmail());
        if (count < 0) throw new EtAuthException("Email id not in Use");
        if(user.getPassword()!=null) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(7));
            user.setPassword(hashedPassword);
        }
            return userRepository.update(user);
    }

    @Override
    public String updateIntroTest(String userId, boolean introTestTaken) throws EtAuthException {
        User user=userRepository.findById(UUID.fromString(userId));
        if(user !=null) {
            return userRepository.updateIntroTest(UUID.fromString(userId),introTestTaken);
        }
        else{
            throw new EtAuthException("Invalid User Id");
        }
    }

    @Override
    public String updateIntroSeen(String userId, boolean introSeen) throws EtAuthException {
        User user=userRepository.findById(UUID.fromString(userId));
        if(user !=null) {
            return userRepository.updateIntroSeen(UUID.fromString(userId),introSeen);
        }
        else{
            throw new EtAuthException("Invalid User Id");
        }
    }
}
