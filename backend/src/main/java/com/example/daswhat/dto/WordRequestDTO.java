package com.example.daswhat.dto;

import com.example.daswhat.entity.Article;
import com.example.daswhat.entity.ProficiencyLevel;
import com.example.daswhat.entity.Topic;

public record WordRequestDTO(
        Article article, String word, String translation, ProficiencyLevel proficiencyLevel, Topic topic
) {}