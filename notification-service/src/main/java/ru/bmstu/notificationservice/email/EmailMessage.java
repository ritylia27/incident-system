package ru.bmstu.notificationservice.email;

import lombok.Data;


@Data
public class EmailMessage {
    private final String email;
    private final String theme;
    private final String body;
}
