package ru.bmstu.incidentservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class IncidentKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(IncidentKafkaProducer.class);

    private final KafkaTemplate<String, IncidentCreatedEvent> kafkaTemplate;

    public IncidentKafkaProducer(KafkaTemplate<String, IncidentCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendIncidentToKafka(IncidentCreatedEvent event) {
        kafkaTemplate.send("incidents", event.id().toString(), event);
        log.info("Incident sent to kafka: id={}", event.id());
    }

}
