package com.example.daswhat.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "words")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Article article;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private String translation;

    @Column(nullable = false)
    private ProficiencyLevel proficiencyLevel;

    @Column(nullable = false)
    private Topic topic;

}
