package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.domain.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;


@Repository
public class UserRepositoryImpl implements UserRepository{

    public static final String SQL_CREATE="INSERT INTO USERS(USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,INTRO_TAKEN,INTRO_SEEN) VALUES (?,?,?,?,?,?,?)";

    public static final String SQL_COUNT_BY_EMAIL="SELECT COUNT(*) FROM USERS WHERE EMAIL = ?";
    public static final String SQL_FIND_BY_USERID=
            "SELECT USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,INTRO_TAKEN,INTRO_SEEN" +
                    " FROM USERS WHERE USER_ID = ?";

    public static final String SQL_FINDBY_EMAIL=
            "SELECT USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,INTRO_TAKEN,INTRO_SEEN " +
                    "FROM USERS WHERE EMAIL = ? ";
    public static final String SQL_UPDATE_USER="UPDATE USERS SET first_name=?,last_name=?,password=? WHERE email=?";
    public static final String SQL_UPDATE_USER1="UPDATE USERS SET first_name=?,last_name=? WHERE email=?";

    public static final String SQL_UPDATE_INTRO_SEEN="UPDATE USERS SET intro_seen = ? WHERE user_id = ?";

    public static final String SQL_UPDATE_INTRO_TEST = "UPDATE USERS SET INTRO_TAKEN = CAST(? AS BOOLEAN) WHERE USER_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UUID create(User user) throws EtAuthException {
        try{
            jdbcTemplate.update(SQL_CREATE, user.getUserId().toString(), user.getFirstName(),
                    user.getLastName(), user.getEmail(), user.getPassword(),user.isIntroTestTaken(),user.isIntroSeen());
            return user.getUserId();
        }catch(Exception e){
            throw new EtAuthException("Invalid Details. Failed to create new User");
        }
    }
    @Override
    public User update(User user) throws EtAuthException{
        try {
            if(user.getPassword()==null)
                jdbcTemplate.update(SQL_UPDATE_USER1, user.getFirstName(), user.getLastName(), user.getEmail());
            else
                jdbcTemplate.update(SQL_UPDATE_USER, user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
            return user;
        }

        catch(EmptyResultDataAccessException e){
            throw new EtAuthException("Email Cannot be Updated");
        }
    }



    @Override
    public User findByEmail(User user) throws EtAuthException {
        try {
            User userDet= jdbcTemplate.queryForObject(SQL_FINDBY_EMAIL, new Object[] {user.getEmail()},userRowMapper);
            return userDet;
        }

        catch(EmptyResultDataAccessException e){
            throw new EtAuthException("Invalid email/password");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL,new Object[]{email},Integer.class);
    }

    @Override
    public User findById(UUID userId) {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_USERID, userRowMapper, userId.toString());
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public String updateIntroTest(UUID userId, boolean introTestTaken) {
        try {
            User user= jdbcTemplate.queryForObject(SQL_FIND_BY_USERID, new Object[] {userId.toString()},userRowMapper);
            jdbcTemplate.update(SQL_UPDATE_INTRO_TEST,introTestTaken,userId.toString() );
            return userId.toString();
        }

        catch(EmptyResultDataAccessException e){
            throw new EtAuthException("Failed to Update Intro test Seen");
        }
    }

    @Override
    public String updateIntroSeen(UUID userId, boolean introSeen) {
        try {
            User user= jdbcTemplate.queryForObject(SQL_FIND_BY_USERID, new Object[] {userId.toString()},userRowMapper);
            jdbcTemplate.update(SQL_UPDATE_INTRO_SEEN,introSeen,userId.toString() );
            return userId.toString();
        }

        catch(EmptyResultDataAccessException e){
            throw new EtAuthException("Failed to Update Intro test Seen");
        }
    }

    private RowMapper<User> userRowMapper =((rs, rowNum)->{
        return new User(
                UUID.fromString(rs.getString("USER_ID")),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getBoolean("INTRO_TAKEN"),
                rs.getBoolean("INTRO_SEEN")
        );
    });
}
