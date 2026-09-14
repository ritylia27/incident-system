package ru.bmstu.incidentservice.DTO;


import lombok.Data;
import ru.bmstu.incidentservice.enums.IncidentPriority;
import ru.bmstu.incidentservice.enums.IncidentStatus;

@Data
public class IncidentListDTO {
    private Long id;
    private String name;
    private String description;
    private IncidentStatus status;
    private IncidentPriority priority;
}
