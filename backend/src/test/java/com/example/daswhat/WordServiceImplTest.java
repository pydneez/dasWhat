package com.example.daswhat;


import com.example.daswhat.dto.WordRequestDTO;
import com.example.daswhat.dto.WordResponseDTO;
import com.example.daswhat.entity.Article;
import com.example.daswhat.entity.ProficiencyLevel;
import com.example.daswhat.entity.Topic;
import com.example.daswhat.entity.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Initializes mocks
class WordServiceImplTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordServiceImpl wordService; // Injects the mock repo into the service

    @Test
    void createWord_ShouldSaveAndReturnDTO() {
        // 1. Arrange (Prepare data)
        WordRequestDTO requestDTO = new WordRequestDTO(
                 Article.DER, "Tisch","Table",ProficiencyLevel.A1, Topic.FURNITURE
        );

        Word savedEntity = Word.builder()
                .id(1L)
                .article(Article.DER)
                .word("Tisch")
                .translation("Table")
                .proficiencyLevel(ProficiencyLevel.A1)
                .topic(Topic.FURNITURE)
                .build();

        // When repo.save() is called with ANY entity, return our 'savedEntity'
        when(wordRepository.save(any(Word.class))).thenReturn(savedEntity);

        // 2. Act (Call the method under test)
        WordResponseDTO result = wordService.createWord(requestDTO);

        // 3. Assert (Verify results)
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Tisch", result.word());
        assertEquals(Article.DER, result.article());

        // Verify the repository was actually called exactly once
        verify(wordRepository, times(1)).save(any(Word.class));
    }
}