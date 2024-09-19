package com.chis.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


/**
 * Service responsible for producing order confirmation messages to the Kafka topic.
 * This class sends {@link PaymentNotificationRequest} objects to a specified Kafka topic ("payment-topic")
 * using Spring Kafka's {@link KafkaTemplate}.
 * This class is part of the Kafka producer-consumer architecture, which helps the system communicate
 * with other microservices or components without tight coupling.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequest request) {
        log.info("Sending notification with body <{}>", request);

        // Build the message to be sent, with the payment notification as the payload
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic") // Set the Kafka topic header
                .build();

        // Send the message to Kafka asynchronously
        kafkaTemplate.send(message);
    }
}
