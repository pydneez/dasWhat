package com.example.daswhat;

import com.example.daswhat.dto.WordRequestDTO;
import com.example.daswhat.dto.WordResponseDTO;
import com.example.daswhat.entity.Article;
import com.example.daswhat.entity.Word;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService{

    private final WordRepository wordRepository;

    // CONSTRUCTOR INJECTION (No @Autowired on fields!)
    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    @Transactional
    public WordResponseDTO createWord(WordRequestDTO request) {
        // 1. map to entity
        Word entity = Word.builder()
                .word(request.word())
                .article(request.article())
                .translation(request.translation())
                .topic(request.topic())
                .proficiencyLevel(request.proficiencyLevel())
                .build();

        // 2. Save to DB
        Word savedEntity = wordRepository.save(entity);

        // 3. Map Entity back to DTO
        return mapToDTO(savedEntity);
    }

    @Override
    public Boolean checkAnswer(Long id, Article article) {
        return null;
    }

    @Override
    public List<WordResponseDTO> listAllWords() {
        return wordRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Helper mapper (In a real app, use MapStruct)
    private WordResponseDTO mapToDTO(Word entity) {
        return new WordResponseDTO(
                entity.getId(),
                entity.getArticle(),
                entity.getWord(),
                entity.getTranslation(),
                entity.getProficiencyLevel(),
                entity.getTopic()
        );
    }

    @Override
    @Transactional
    public List<WordResponseDTO> createWordsBatch(List<WordRequestDTO> requests) {
        // 1. Map all DTOs to Entities
        List<Word> entities = requests.stream()
                .map(req -> Word.builder()
                        .word(req.word())
                        .article(req.article())
                        .translation(req.translation())
                        .topic(req.topic())
                        .proficiencyLevel(req.proficiencyLevel())
                        .build())
                .collect(Collectors.toList());

        // 2. Save All (Performance: This is faster than saving one by one)
        List<Word> savedEntities = wordRepository.saveAll(entities);

        // 3. Map back to DTOs
        return savedEntities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
