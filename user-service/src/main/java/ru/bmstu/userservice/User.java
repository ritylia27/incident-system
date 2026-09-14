package ru.bmstu.userservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column  (name = "role")
    private UserRole role;

    @Column (name = "user_full_name")
    private String userFullName;

    @Column (name = "user_login")
    private String userLogin;

    @Column (name = "phone_number")
    private String phoneNumber;

    @Column (name = "email")
    private String email;

    @Column (name = "workplace")
    private String workplace;
}
