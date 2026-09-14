package ru.bmstu.userservice;

import org.mapstruct.*;
import ru.bmstu.userservice.DTOs.UserCreateDTO;
import ru.bmstu.userservice.DTOs.UserDTO;
import ru.bmstu.userservice.DTOs.UserNotificationResponse;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {
    UserDTO toDTO(User user);

    User toEntity(UserCreateDTO userDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateFromUserDTO(
            UserDTO userDTO,
            @MappingTarget User user
    );

    UserNotificationResponse toNotificationResponse(User user);
}
