package com.example.suggestion_service.repository;

import com.example.suggestion_service.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SuggestionRepository extends JpaRepository<Suggestion, UUID> {
    List<Suggestion> findByUserIdOrderByCreatedOnDesc(UUID userId);
    List<Suggestion> findTop7ByUserIdOrderByCreatedOnDesc(UUID userId);
    Suggestion findTopByUserIdOrderByCreatedOnDesc(UUID userId);
}
