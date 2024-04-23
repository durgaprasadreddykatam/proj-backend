package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.Entities.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepo extends JpaRepository<Question, UUID> {

    @Query("select count(u) from questions u where u.questionId = :Id")
    int getCountById(UUID Id);

    @Query("select q from questions  q where q.questionType = :type order by function('random')")
    List<Question> fetchByCountAndType(@Param("type") String type, Pageable pageable);



}
