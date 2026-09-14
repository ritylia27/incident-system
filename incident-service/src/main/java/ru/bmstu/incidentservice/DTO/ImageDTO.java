package ru.bmstu.incidentservice.DTO;

import lombok.Data;

@Data
public class ImageDTO {
    private Long id;
    private String URL;
    private String fileName;
    private String size;
    private String mediaType;
}
