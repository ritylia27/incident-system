package ru.bmstu.userservice.DTOs;

import lombok.Data;
import ru.bmstu.userservice.UserRole;

@Data
public class UserDTO {
    private Long id;
    private UserRole role;
    private String userFullName;
    private String userLogin;
    private String phoneNumber;
    private String email;
    private String workplace;
}
