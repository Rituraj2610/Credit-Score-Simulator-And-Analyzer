package com.example.suggestion_service.service;

import com.example.suggestion_service.dto.SuggestionRequestAddDTO;
import com.example.suggestion_service.dto.SuggestionResponseDTO;
import com.example.suggestion_service.entity.Suggestion;
import com.example.suggestion_service.repository.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;

    // SCHEDULER WILL CALL THIS ENDPOINT NOT THE USER; SO MAKE RETURN TYPE ACC TO SCHEDULER
    public SuggestionResponseDTO addSuggestion(SuggestionRequestAddDTO dto) {
        List<Suggestion> userSuggestions = suggestionRepository.findTop7ByUserIdOrderByCreatedOnDesc(dto.getUserId());
        if (userSuggestions.size() == 7) {
            suggestionRepository.delete(userSuggestions.get(userSuggestions.size() - 1));
        }

        Suggestion suggestion = new Suggestion();
        suggestion.setUserId(dto.getUserId());
        suggestion.setSuggestion(dto.getSuggestion());
        suggestion.setReason(dto.getReason());

        Suggestion saved = suggestionRepository.save(suggestion);

        SuggestionResponseDTO response = new SuggestionResponseDTO();
//        response.setId(saved.getId());
//        response.setUserId(saved.getUserId());
        response.setSuggestion(saved.getSuggestion());
        response.setReason(saved.getReason());
        response.setCreatedOn(saved.getCreatedOn());
        return response;
    }

    public SuggestionResponseDTO getLatestSuggestion(UUID userId) {
        Suggestion suggestion = suggestionRepository.findTopByUserIdOrderByCreatedOnDesc(userId);
        if (suggestion == null) {
            throw new RuntimeException("No suggestions found for user");
        }
        SuggestionResponseDTO response = new SuggestionResponseDTO();
        response.setSuggestion(suggestion.getSuggestion());
        response.setReason(suggestion.getReason());
        response.setCreatedOn(suggestion.getCreatedOn());
        return response;
    }

    public List<SuggestionResponseDTO> getLast7Suggestions(UUID userId) {
        List<Suggestion> suggestions = suggestionRepository.findTop7ByUserIdOrderByCreatedOnDesc(userId);
        return suggestions.stream().map(s -> {
            SuggestionResponseDTO dto = new SuggestionResponseDTO();
//            dto.setId(s.getId());
            dto.setSuggestion(s.getSuggestion());
            dto.setReason(s.getReason());
            dto.setCreatedOn(s.getCreatedOn());
            return dto;
        }).collect(Collectors.toList());
    }
}