package ru.bmstu.notificationservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.bmstu.notificationservice.UserDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceClient {
    private final RestClient userServiceRestClient;

    public List<UserDTO> findAnalysts() {
        List<UserDTO> analyst = userServiceRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user/role")
                        .queryParam("role", "ANALYST")
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return analyst;
    }
}
