package com.example.OXQuiz.repositary.service;

import com.example.OXQuiz.dto.QuizDto;
import com.example.OXQuiz.entity.Quiz;
import com.example.OXQuiz.repositary.OXQuizRepositary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OXQuizService {
    Random random = new Random();
    private final OXQuizRepositary oxQuizRepositary;

    public OXQuizService(OXQuizRepositary oxQuizRepositary) {
        this.oxQuizRepositary = oxQuizRepositary;
    }

    public void submitQuiz(QuizDto dto) {
        Quiz quiz = dto.fromQuizDto(dto);
        oxQuizRepositary.save(quiz);
    }

    public List<QuizDto> showList() {
        List<QuizDto> dtos = oxQuizRepositary.findAll()
                .stream()
                .map(x -> QuizDto.fromQuiz(x))
                .toList();
        return dtos;
    }

    public QuizDto findById(Long id) {
        return QuizDto.fromQuiz(oxQuizRepositary.findById(id).orElse(null));
    }

    public void update(Quiz quiz) {
        oxQuizRepositary.save(quiz);
    }

    public void deleteById(Long id) {
        oxQuizRepositary.deleteById(id);
    }

    public QuizDto playQuiz() {
        return QuizDto.fromQuiz(oxQuizRepositary.random());
    }

}
