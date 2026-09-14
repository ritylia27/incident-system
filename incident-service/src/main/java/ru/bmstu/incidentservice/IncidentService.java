package ru.bmstu.incidentservice;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bmstu.incidentservice.DTO.*;
import ru.bmstu.incidentservice.enums.IncidentCategory;
import ru.bmstu.incidentservice.enums.IncidentPriority;
import ru.bmstu.incidentservice.enums.IncidentStatus;
import ru.bmstu.incidentservice.enums.ResponsibleService;
import ru.bmstu.incidentservice.kafka.IncidentCreatedEvent;
import ru.bmstu.incidentservice.kafka.IncidentKafkaProducer;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IncidentService {
    private final IncidentMapper mapper;
    private final IncidentRepository repository;
    private final IncidentKafkaProducer incidentKafkaProducer;
    private final Logger log = LoggerFactory.getLogger(IncidentService.class);

    public IncidentDTO createIncident(IncidentUserDTO dto) {
        Incident createdIncident = mapper.toEntity(dto);
        createdIncident.setStatus(IncidentStatus.OPEN);
        createdIncident.setDateCreate(LocalDate.now());

        Incident savedIncident = repository.save(createdIncident);
        log.info("Incident id = {} saved to incident-db", savedIncident.getId());

        IncidentCreatedEvent event = mapper.toCreatedEvent(savedIncident);

        incidentKafkaProducer.sendIncidentToKafka(event);
        return mapper.toDTO(savedIncident);
    }

    public List<IncidentListDTO> findAll() {
        List<Incident> incidents = repository.findAll();
        return incidents.stream().map(mapper::toListDTO).toList();
    }

    public IncidentDetailDTO findById(Long id) {
        Incident gettingIncident = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found incident with id = " + id));


        // Тут не хватает запросов к сервисам пользователей и картинок

        UserDTO initiatorDTO = new UserDTO();
        UserDTO analystDTO = new UserDTO();
        List<ImageDTO> images = new ArrayList<>();

        return mapper.toDetailDTO(
                gettingIncident,
                initiatorDTO,
                analystDTO,
                images);
    }

    public IncidentDTO updateIncident(Long id, IncidentDTO newDTO) {
        // Тут стоит включить проверку на роль находящегося в системе пользователя
        // И исходя из этого выбрать стратегию обновления: для юзера или для аналитика

        Incident incident = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found incident with id = " + id));
        mapper.updateFromIncidentDTO(newDTO, incident);
        Incident savedIncident = repository.save(incident);

        return mapper.toDTO(savedIncident);

//        Incident incident = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Not found incident with id = " + id));
//        mapper.updateFromIncidentUserDTO(newDTO, incident);
//        Incident savedIncident = repository.save(incident);
//
//        return mapper.toDTO(savedIncident);
    }

    public IncidentDTO setCategory(Long id, String category) {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found incident with id = " + id));
        incident.setCategory(IncidentCategory.valueOf(category.toUpperCase()));
        Incident savedIncident = repository.save(incident);

        return mapper.toDTO(savedIncident);
    }

    public IncidentDTO setStatus(Long id, String status) {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found incident with id = " + id));
        incident.setStatus(IncidentStatus.valueOf(status.toUpperCase()));
        Incident savedIncident = repository.save(incident);

        return mapper.toDTO(savedIncident);
    }

    public IncidentDTO setAnalyst(Long id, Long analystId) {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found incident with id = " + id));


        // Тут тоже не хватает запроса в сервис юзеров
        incident.setAnalystId(analystId);
        Incident savedIncident = repository.save(incident);

        return mapper.toDTO(savedIncident);

    }

    public IncidentDTO setPriority(Long id, String priority) {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found incident with id = " + id));


        // Тут тоже не хватает запроса в сервис юзеров
        incident.setPriority(IncidentPriority.valueOf(priority.toUpperCase()));
        Incident savedIncident = repository.save(incident);

        return mapper.toDTO(savedIncident);
    }

    public IncidentDTO setResponsibleService(Long id, String responsibleService) {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found incident with id = " + id));


        // Тут тоже не хватает запроса в сервис юзеров
        incident.setResponsibleService(ResponsibleService.valueOf(responsibleService.toUpperCase()));
        Incident savedIncident = repository.save(incident);

        return mapper.toDTO(savedIncident);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Not found incident with id = " + id);
        };

    }
}
