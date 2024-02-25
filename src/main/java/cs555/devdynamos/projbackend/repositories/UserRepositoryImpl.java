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

    public static final String SQL_CREATE="INSERT INTO USERS(USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD) VALUES (?,?,?,?,?)";

    public static final String SQL_COUNT_BY_EMAIL="SELECT COUNT(*) FROM USERS WHERE EMAIL = ?";
    public static final String SQL_FIND_BY_USERID=
            "SELECT USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD" +
                    " FROM USERS WHERE USER_ID = ?";

    public static final String SQL_FINDBY_EMAIL=
            "SELECT USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD " +
                    "FROM USERS WHERE EMAIL = ? ";
    public static final String SQL_UPDATE_USER="UPDATE USERS SET first_name=?,last_name=?,password=? WHERE email=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UUID create(String firstName, String lastName, String email, String password) throws EtAuthException {
        String hashedpassword = BCrypt.hashpw(password,BCrypt.gensalt(7));
        try{
            UUID userId=UUID.randomUUID();
            jdbcTemplate.update(SQL_CREATE, userId.toString(), firstName, lastName, email, hashedpassword);
            return userId;
        }catch(Exception e){
            throw new EtAuthException("Invalid Details. Failed to create new User");
        }
    }
    @Override
    public User update(String firstName, String lastName, String email, String password) throws EtAuthException{
        String hashedPassword=BCrypt.hashpw(password,BCrypt.gensalt(7));
        try {
            User user= jdbcTemplate.queryForObject(SQL_FINDBY_EMAIL, new Object[] {email},userRowMapper);
            jdbcTemplate.update(SQL_UPDATE_USER, firstName, lastName, hashedPassword, email);
            return user;
        }

        catch(EmptyResultDataAccessException e){
            throw new EtAuthException("Email Cannot be Updated");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        try {
            User user= jdbcTemplate.queryForObject(SQL_FINDBY_EMAIL, new Object[] {email},userRowMapper);
            if(!BCrypt.checkpw(password,user.getPassword()))
                throw new EtAuthException("Invalid email/password");
            return user;
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
    private RowMapper<User> userRowMapper =((rs, rowNum)->{
        return new User(
                UUID.fromString(rs.getString("USER_ID")),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD")
        );
    });
}
