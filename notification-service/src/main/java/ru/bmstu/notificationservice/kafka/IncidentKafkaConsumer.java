package ru.bmstu.notificationservice.kafka;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.bmstu.notificationservice.IncidentCreatedEvent;
import ru.bmstu.notificationservice.NotificationService;

@Service
@RequiredArgsConstructor
public class IncidentKafkaConsumer {
    private final NotificationService notificationService;
    Logger log = LoggerFactory.getLogger(IncidentKafkaConsumer.class);


    @KafkaListener(topics = "incidents")
    public void consumeIncident(ConsumerRecord<String, IncidentCreatedEvent> record) {
        log.info("Received incident: incident = {}, key = {}, partition = {}",
                record.value(),
                record.key(),
                record.partition());
        notificationService.processIncidentCreated(record.value());
    }
}
