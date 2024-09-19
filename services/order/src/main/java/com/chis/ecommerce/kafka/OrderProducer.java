package com.chis.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Service responsible for producing order confirmation messages to the Kafka topic.
 * This class sends {@link OrderConfirmation} objects to a specified Kafka topic ("order-topic")
 * using Spring Kafka's {@link KafkaTemplate}.
 * This class is part of the Kafka producer-consumer architecture, which helps the system communicate
 * with other microservices or components without tight coupling.
 * For example, an order service might send an order confirmation to notify other services about
 * the order's successful completion, such as triggering an email notification or billing process.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("Sending order confirmation");

        // Build the message to be sent, with the order confirmation as the payload
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "order-topic") // Set the Kafka topic header
                .build();

        // Send the message to Kafka asynchronously
        kafkaTemplate.send(message);
    }
}
