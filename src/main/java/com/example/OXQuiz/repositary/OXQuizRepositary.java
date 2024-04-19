package com.example.OXQuiz.repositary;

import com.example.OXQuiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OXQuizRepositary extends JpaRepository<Quiz,Long> {

    @Query(value = "SELECT * FROM quiz order by rand() limit 1", nativeQuery = true)
    Quiz random();
}
