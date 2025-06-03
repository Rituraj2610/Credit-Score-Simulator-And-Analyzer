package com.example.suggestion_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SuggestionResponseDTO {
    private String suggestion;
    private String reason;
    private LocalDateTime createdOn;
}
