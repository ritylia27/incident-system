package ru.bmstu.incidentservice.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String role;
    private String userFullName;
    private String phoneNumber;
    private String email;
    private String workplace;
}
