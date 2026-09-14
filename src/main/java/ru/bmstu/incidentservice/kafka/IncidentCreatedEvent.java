package ru.bmstu.incidentservice.kafka;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record IncidentCreatedEvent(
        UUID eventId,
        Instant occurredAt,
        Long id,
        String name,
        String description,
        LocalDate dateCreate,
        LocalDate dateClosed,
        Long analystId,
        Long initiatorId,
        String status,
        String priority,
        String category,
        String responsibleService
) {
}
