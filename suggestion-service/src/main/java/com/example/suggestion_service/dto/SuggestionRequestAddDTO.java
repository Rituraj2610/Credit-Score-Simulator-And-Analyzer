package com.example.suggestion_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SuggestionRequestAddDTO {
    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotBlank(message = "Suggestion text is required")
    private String suggestion;

    private String reason;

    private String suggestionText;
}
