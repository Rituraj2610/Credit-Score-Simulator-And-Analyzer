package com.example.suggestion_service.scheduler;

import com.example.suggestion_service.dto.SuggestionRequestAddDTO;
import com.example.suggestion_service.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SuggestionScheduler {

    private final SuggestionService suggestionService;

    @Scheduled(cron = "0 0 0 1 * ?") // Run at midnight on the 1st of every month
    public void generateMonthlySuggestions() {
        SuggestionRequestAddDTO dto = new SuggestionRequestAddDTO();
        dto.setUserId(UUID.randomUUID());
        dto.setSuggestionText("Reduce credit card utilization below 30%");
        suggestionService.addSuggestion(dto);
    }
}