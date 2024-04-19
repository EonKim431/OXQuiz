package com.example.OXQuiz.dto;

import com.example.OXQuiz.entity.Quiz;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class QuizDto {
    private Long id;

    @Size(max = 100,min = 1,message = "공백일 수 없습니다")
    private String quiz;
    @NotNull(message = "정답을 체크해주세요")
    private String answer;
    @NotBlank(message = "작성자를 입력해주세요")
    private String writer;

    public Quiz fromQuizDto(QuizDto dto) {
        Quiz quiz = new Quiz();
        quiz.setQuiz(dto.getQuiz());
        quiz.setId(dto.getId());
        quiz.setAnswer(dto.getAnswer());
        quiz.setWriter(dto.getWriter());
        return quiz;
    }
    public static QuizDto fromQuiz(Quiz quiz){
        return new QuizDto(
                quiz.getId(),
                quiz.getQuiz(),
                quiz.getAnswer(),
                quiz.getWriter()
                );
    }
}
