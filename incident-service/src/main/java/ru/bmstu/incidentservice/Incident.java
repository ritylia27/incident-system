package ru.bmstu.incidentservice;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.bmstu.incidentservice.enums.IncidentCategory;
import ru.bmstu.incidentservice.enums.IncidentPriority;
import ru.bmstu.incidentservice.enums.IncidentStatus;
import ru.bmstu.incidentservice.enums.ResponsibleService;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "incidents")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "description")
    private String description;

    @Column (name = "date_create")
    private LocalDate dateCreate;

    @Column (name = "date_closed")
    private LocalDate dateClosed;

    @Column (name = "analyst_id")
    private Long analystId;

    @Column (name = "initiator_id")
    private Long initiatorId;

    @Enumerated(EnumType.STRING)
    @Column  (name = "status")
    private IncidentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private IncidentPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private IncidentCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "responsible_service")
    private ResponsibleService responsibleService;
}