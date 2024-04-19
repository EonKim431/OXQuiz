package com.example.OXQuiz.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity @Data @ToString
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @Column(length = 100,nullable = false) private String quiz;
    @Column(nullable = false) private String answer;
    @Column(length = 20,nullable = false) private String Writer;


}
