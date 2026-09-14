package ru.bmstu.userservice;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bmstu.userservice.DTOs.UserCreateDTO;
import ru.bmstu.userservice.DTOs.UserDTO;
import ru.bmstu.userservice.DTOs.UserNotificationResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final UserRepository repository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public UserDTO createUser(UserCreateDTO dto) {
        User createdUser = mapper.toEntity(dto);
        User savedUser = repository.save(createdUser);
        log.info("User with id = {} was created", savedUser.getId());
        return mapper.toDTO(savedUser);
    }


    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found user with id = " + id));
        mapper.updateFromUserDTO(dto, user);
        User newUser = repository.save(user);

        log.info("User with id = {} was update", newUser.getId());

        return mapper.toDTO(newUser);
    }

    public void deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found user with id = " + id));

        repository.deleteById(id);
        log.info("User with id = {} was delete", id);
    }

    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();
        return users.stream().map(mapper::toDTO).toList();
    }

    public List<UserNotificationResponse> findAllByRole(UserRole role) {
        List<User> usersWithRole = repository.findAllByRole(role);
        return usersWithRole.stream().map(mapper::toNotificationResponse).toList();
    }

    public UserDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found user with id = " + id));
        return mapper.toDTO(user);
    }
}
