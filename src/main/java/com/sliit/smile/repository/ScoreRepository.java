package com.sliit.smile.repository;

import com.sliit.smile.model.Score;
import com.sliit.smile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query(value = "select * from score order by  score desc limit 10",nativeQuery = true)
    List<Score> findFirst10ByScoreOrderByScoreIdDesc();

    Score findByUser(User user);
}
