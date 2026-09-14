package ru.bmstu.incidentservice.DTO;
import lombok.Data;
import ru.bmstu.incidentservice.enums.IncidentCategory;
import ru.bmstu.incidentservice.enums.IncidentPriority;
import ru.bmstu.incidentservice.enums.IncidentStatus;
import ru.bmstu.incidentservice.enums.ResponsibleService;

import java.time.LocalDate;

@Data
public class IncidentDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate dateCreate;
    private LocalDate dateClosed;

    private Long analystId;
    private Long initiatorId;

    private IncidentStatus status;
    private IncidentPriority priority;
    private IncidentCategory category;
    private ResponsibleService responsibleService;
}
