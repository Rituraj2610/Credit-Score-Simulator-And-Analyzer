package com.example.suggestion_service.controller;

import com.example.suggestion_service.dto.SuggestionRequestAddDTO;
import com.example.suggestion_service.dto.SuggestionResponseDTO;
import com.example.suggestion_service.dto.SuggestionResponseFetchDTO;
import com.example.suggestion_service.service.SuggestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/suggestion")
@RequiredArgsConstructor
public class SuggestionController {

    private final SuggestionService suggestionService;

    @GetMapping("/user")
    public SuggestionResponseFetchDTO getLatestSuggestion(@RequestParam UUID userId) {
        return suggestionService.getLatestSuggestion(userId);
    }

    @GetMapping("/user-history")
    public List<SuggestionResponseFetchDTO> getLast7Suggestions(@RequestParam UUID userId) {
        return suggestionService.getLast7Suggestions(userId);
    }

    @PostMapping("/user")
    public SuggestionResponseDTO addSuggestion(@Valid @RequestBody SuggestionRequestAddDTO dto) {
        return suggestionService.addSuggestion(dto);
    }
}