package ru.bmstu.notificationservice;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bmstu.notificationservice.api.UserServiceClient;
import ru.bmstu.notificationservice.email.EmailFactory;
import ru.bmstu.notificationservice.email.EmailMessage;
import ru.bmstu.notificationservice.email.EmailService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;
    private final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final UserServiceClient userServiceClient;

    public void processIncidentCreated(IncidentCreatedEvent event) {
        List<UserDTO> analysts = userServiceClient.findAnalysts();
        log.info("Get list active analysts!");

//        if (analysts.isEmpty()) {
//            log.warn(
//                    "No analysts found for incident id={}",
//                    event.getId()
//            );
//            return;
//        }

        for (UserDTO analyst : analysts) {
            emailService.sendEmailToAnalyst(event, analyst);
        }
    }


}
