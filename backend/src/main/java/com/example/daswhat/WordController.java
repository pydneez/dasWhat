package com.example.daswhat;

import com.example.daswhat.dto.WordRequestDTO;
import com.example.daswhat.dto.WordResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/words")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping
    public ResponseEntity<WordResponseDTO> createNewWord(@RequestBody WordRequestDTO request){
        WordResponseDTO newWord = wordService.createWord(request);
        return new ResponseEntity<>(newWord, HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<WordResponseDTO>> createWordsBatch(@RequestBody List<WordRequestDTO> requests) {
        return new ResponseEntity<>(wordService.createWordsBatch(requests), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<WordResponseDTO>> listAllWords(){
        return ResponseEntity.ok(wordService.listAllWords());
    }



}
