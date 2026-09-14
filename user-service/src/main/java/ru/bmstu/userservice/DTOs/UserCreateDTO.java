package ru.bmstu.userservice.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.bmstu.userservice.UserRole;

@Data
public class UserCreateDTO {

    @NotNull(message = "Role is required")
    private UserRole role;

    @NotBlank(message = "Full name is required")
    private String userFullName;

    @NotBlank(message = "Login is required")
    private String userLogin;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Workplace is required")
    private String workplace;
}
