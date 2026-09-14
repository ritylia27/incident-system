package ru.bmstu.incidentservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.incidentservice.DTO.IncidentDTO;
import ru.bmstu.incidentservice.DTO.IncidentDetailDTO;
import ru.bmstu.incidentservice.DTO.IncidentListDTO;
import ru.bmstu.incidentservice.DTO.IncidentUserDTO;
import ru.bmstu.incidentservice.IncidentService;

import java.util.List;

@RestController
@RequestMapping("/incident")
@RequiredArgsConstructor
public class IncidentController {
    private final IncidentService service;

    @PostMapping
    public IncidentDTO createIncident(@RequestBody IncidentUserDTO dto) {
        return service.createIncident(dto);
    }

    @GetMapping
    public List<IncidentListDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public IncidentDetailDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    public IncidentDTO updateIncident(@PathVariable Long id,
                                      @RequestBody IncidentDTO newDTO) {
        return service.updateIncident(id, newDTO);
    }

    @PatchMapping("/{id}/category")
    public IncidentDTO setCategory(@PathVariable Long id,
                                   @RequestBody String category) {
        return service.setCategory(id, category);
    }

    @PatchMapping("/{id}/status")
    public IncidentDTO setStatus(@PathVariable Long id,
                                 @RequestBody String status) {
        return service.setStatus(id, status);
    }

    @PatchMapping("/{id}/analyst")
    public IncidentDTO setAnalyst(@PathVariable Long id,
                                  @RequestBody String analystId) {
        return service.setAnalyst(id, Long.parseLong(analystId));
    }

    @PatchMapping("/{id}/priority")
    public IncidentDTO setPriority(@PathVariable Long id,
                                  @RequestBody String priority) {
        return service.setPriority(id, priority);
    }

    @PatchMapping("/{id}/responsibleservice")
    public IncidentDTO setResponsibleService(@PathVariable Long id,
                                   @RequestBody String responsibleService) {
        return service.setResponsibleService(id, responsibleService);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
