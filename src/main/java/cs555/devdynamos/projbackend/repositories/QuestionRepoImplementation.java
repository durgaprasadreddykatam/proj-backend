package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.domain.Question;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class QuestionRepoImplementation implements IQuestionRepository{
    public static final String SQL_CREATE="INSERT INTO Questions(questionid,question,answer ,questiontype) VALUES (?,?,?,?)";

    public static final String SQL_UPDATE="UPDATE Questions SET question = ?, answer = ?, questiontype = ? WHERE questionid = ?";

    public static final String SQL_DELETE = "DELETE FROM Questions WHERE questionid = ?";

    public static final String SQL_FETCH = "SELECT * FROM Questions WHERE questionid = ?";
    public static final String SQL_FETCH_RANDOM = "SELECT * FROM Questions WHERE questiontype =?  ORDER BY RANDOM() LIMIT ?";

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public UUID addQuestion(Question question) {
        try {
            jdbcTemplate.update(SQL_CREATE, question.questionId(), question.question(), question.answer(), question.questionType());
            return question.questionId();
        } catch (Exception e) {
            throw new EtAuthException("Invalid Details. Failed to add Question");
        }
    }

    @Override
    public void deleteQuestion(UUID questionID) {
        try {
            jdbcTemplate.update(SQL_DELETE, questionID);
        } catch (Exception e) {
            throw new EtAuthException("Failed to Delete Question");
        }

    }

    @Override
    public Question fetchQuestion(UUID questionID) {
        try {
            return jdbcTemplate.queryForObject(SQL_FETCH, new Object[]{questionID}, calcQuestionRowMapper);
        } catch (Exception e) {
            throw new EtAuthException("Failed to fetch question");
        }
    }

    @Override
    public UUID updateQuestion(Question question) {
        try {
            jdbcTemplate.update(SQL_UPDATE,question.question(),question.answer(),question.questionType(),question.questionId() );
            return question.questionId();
        } catch (Exception e) {
            throw new EtAuthException("Failed to Update Question");
        }
    }

    @Override
    public List<Question> fetchQuestions(String type, int count) {
        try {
            return jdbcTemplate.query(SQL_FETCH_RANDOM, new Object[]{type, count}, calcQuestionRowMapper);
        } catch (Exception e) {
            throw new EtAuthException("Failed to fetch random questions");
        }
    }

    @Override
    public String addQuestions(List<Question> questions) {
        try {
            for (Question question : questions) {
                UUID questionId=UUID.randomUUID();
                jdbcTemplate.update(SQL_CREATE, questionId, question.question(), question.answer(), question.questionType());
            }
            return "Succesfully Added List Of Questions in Database";
        } catch (Exception e) {
            throw new EtAuthException("Invalid Details. Failed to add Questions");
        }
    }

    private RowMapper<Question> calcQuestionRowMapper =((rs, rowNum)->{
        return new Question(
                rs.getObject("questionId", UUID.class),
                rs.getString("question"),
                rs.getString("answer"),
                rs.getString("questionType")
        );
    });
}
