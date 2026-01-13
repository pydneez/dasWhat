package com.example.daswhat;

import com.example.daswhat.dto.WordRequestDTO;
import com.example.daswhat.dto.WordResponseDTO;
import com.example.daswhat.entity.Article;

import java.util.List;

public interface WordService {
    WordResponseDTO createWord(WordRequestDTO word);
    List<WordResponseDTO> createWordsBatch(List<WordRequestDTO> requests);
    List<WordResponseDTO> listAllWords();
    Boolean checkAnswer(Long id, Article article);
}
