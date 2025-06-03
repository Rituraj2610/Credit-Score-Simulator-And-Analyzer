package com.example.suggestion_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SuggestionResponseFetchDTO {
    private UUID id;
    private String suggestion;
    private String reason;
    private LocalDateTime createdOn;
}
