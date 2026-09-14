package ru.bmstu.userservice.DTOs;

import lombok.Data;

@Data
public class UserNotificationResponse {
    private Long id;
    private String userFullName;
    private String email;
}
