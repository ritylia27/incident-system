package ru.bmstu.notificationservice;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class IncidentCreatedEvent {
    private UUID eventId;
    private Instant occurredAt;
    private Long id;
    private String name;
    private String description;
    private LocalDate dateCreate;
    private LocalDate dateClosed;
    private Long analystId;
    private Long initiatorId;
    private String status;
    private String priority;
    private String category;
    private String responsibleService;
}
