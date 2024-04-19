package com.example.OXQuiz.repositary;

import com.example.OXQuiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OXQuizRepositary extends JpaRepository<Quiz,Long> {
}
