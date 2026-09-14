package ru.bmstu.incidentservice;


import org.mapstruct.*;
import ru.bmstu.incidentservice.DTO.*;
import ru.bmstu.incidentservice.kafka.IncidentCreatedEvent;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface IncidentMapper {

    IncidentListDTO toListDTO (Incident incident);


    IncidentDTO toDTO(Incident incident);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.SET_TO_NULL
    )
    @Mapping(target = "id", ignore = true)
    Incident toEntity(IncidentUserDTO incidentUserDTO);

    @BeanMapping(
            ignoreByDefault = true,
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "analystId", source = "analystId")
    @Mapping(target = "dateClosed", source = "dateClosed")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "responsibleService", source = "responsibleService")
    void updateFromIncidentDTO(
            IncidentDTO incidentDTO,
            @MappingTarget Incident incident
    );


    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "incident.id")
    @Mapping(target = "name", source = "incident.name")
    @Mapping(target = "description", source = "incident.description")
    @Mapping(target = "dateCreate", source = "incident.dateCreate")
    @Mapping(target = "dateClosed", source = "incident.dateClosed")
    @Mapping(target = "status", source = "incident.status")
    @Mapping(target = "priority", source = "incident.priority")
    @Mapping(target = "category", source = "incident.category")
    @Mapping(
            target = "responsibleService",
            source = "incident.responsibleService"
    )
    @Mapping(target = "analyst", source = "analyst")
    @Mapping(target = "initiator", source = "initiator")
    @Mapping(target = "images", source = "images")
    IncidentDetailDTO toDetailDTO(
            Incident incident,
            UserDTO analyst,
            UserDTO initiator,
            List<ImageDTO> images
    );


    @Mapping(
            target = "eventId",
            expression = "java(java.util.UUID.randomUUID())"
    )
    @Mapping(
            target = "occurredAt",
            expression = "java(java.time.Instant.now())"
    )
    @Mapping(target = "status", source = "status")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "category", source = "category")
    @Mapping(
            target = "responsibleService",
            source = "responsibleService"
    )
    IncidentCreatedEvent toCreatedEvent(Incident incident);
}
