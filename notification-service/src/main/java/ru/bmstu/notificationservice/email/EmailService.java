package ru.bmstu.notificationservice.email;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.bmstu.notificationservice.IncidentCreatedEvent;
import ru.bmstu.notificationservice.UserDTO;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailFactory emailFactory;
    private final JavaMailSender mailSender;
    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    private @Value("${notification-service.mail.from}") String emailFrom;

    public void sendEmailToAnalyst(IncidentCreatedEvent event, UserDTO analyst) {


        EmailMessage email = emailFactory.createIncidentCreateEmail(event, analyst);


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailFrom);
        mailMessage.setTo(email.getEmail());
        mailMessage.setSubject(email.getTheme());
        mailMessage.setText(email.getBody());


        mailSender.send(mailMessage);

        log.info("Send email to {}", email.getEmail());
        log.info(email.getTheme());
        log.info(email.getBody());
    }
}
