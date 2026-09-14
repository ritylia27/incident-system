package ru.bmstu.userservice;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.userservice.DTOs.UserCreateDTO;
import ru.bmstu.userservice.DTOs.UserDTO;
import ru.bmstu.userservice.DTOs.UserNotificationResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserCreateDTO dto) {
        return service.createUser(dto);
    }

    @PatchMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id,
                              @RequestBody UserDTO dto) {
        return service.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/role")
    public List<UserNotificationResponse> findAllByRole(@RequestParam UserRole role) {
        return service.findAllByRole(role);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
