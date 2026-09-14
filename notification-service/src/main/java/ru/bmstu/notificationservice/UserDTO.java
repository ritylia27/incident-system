package ru.bmstu.notificationservice;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String userFullName;
    private String email;
}

