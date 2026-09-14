package ru.bmstu.notificationservice.email;


import org.springframework.stereotype.Component;
import ru.bmstu.notificationservice.IncidentCreatedEvent;
import ru.bmstu.notificationservice.UserDTO;

@Component
public class EmailFactory {
    public EmailMessage createIncidentCreateEmail(IncidentCreatedEvent event, UserDTO analyst) {
        String theme = "Создан новый инцидент id = %s".formatted(event.getId());
        String body = """
                Здравствуйте, %s !
                
                Был создан новый инцидент!
                
                Номер: %s
                Название: %s
                Описание: %s
                Дата создания: %s
                """.formatted(
                        analyst.getUserFullName(),
                        event.getId(),
                        event.getName(),
                        event.getDescription(),
                        event.getDateCreate()
                );

        return new EmailMessage(
                analyst.getEmail(),
                theme,
                body);
    };

}
