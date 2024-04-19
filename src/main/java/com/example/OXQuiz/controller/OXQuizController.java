package com.example.OXQuiz.controller;

import com.example.OXQuiz.dto.QuizDto;
import com.example.OXQuiz.repositary.service.OXQuizService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller @RequestMapping("/quiz") @Slf4j
public class OXQuizController {
    private final OXQuizService oxQuizService;

    public OXQuizController(OXQuizService oxQuizService) {
        this.oxQuizService = oxQuizService;
    }

    @GetMapping("/submit")
    public String submit(Model model){
        model.addAttribute("dto", new QuizDto());
        return "submitForm";
    }
    @PostMapping("/submit")
    public String submit(@Valid @ModelAttribute("dto")QuizDto dto,
                        BindingResult bindingResult,
                        Model model){
        if(bindingResult.hasErrors()){
            log.info("=====Validation Error=====");
            return "submitForm";
        }
        model.addAttribute("dto", new QuizDto());
        log.info(dto.toString());
        oxQuizService.submitQuiz(dto);
        return "submitForm";
    }

    @GetMapping("")
    public String show(Model model){
        List<QuizDto> quizDtos = oxQuizService.showList();
        model.addAttribute("dtos",quizDtos);
        return "show";
    }
    @GetMapping("{id}")
    public String update(@PathVariable("id")Long id,
                         Model model){
        model.addAttribute("dto",
                oxQuizService.findById(id)
                );
        return "update";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dto") QuizDto dto,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("=====Validation Error=====");
            return "update";
        }
        oxQuizService.update(dto.fromQuizDto(dto));
        return "redirect:/quiz";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("updateId")Long id){
        oxQuizService.deleteById(id);
        return "redirect:/quiz";
    }
    @GetMapping("play")
    public String play(Model model){
        QuizDto dto = oxQuizService.playQuiz();
        model.addAttribute("dto",dto);
        return "play";
    }
    @PostMapping("check")
    public String check(@RequestParam("answer")String answer,
                        @RequestParam("dtoAnswer")String dtoAnswer,
                        Model model){
        if (dtoAnswer.equals(answer)){
            model.addAttribute("text","정답입니다");
        }else {
            model.addAttribute("text","오답입니다");
        }
        return "check";
    }
}
