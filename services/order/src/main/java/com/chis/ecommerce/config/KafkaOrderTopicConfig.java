package com.chis.ecommerce.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration class for setting up a Kafka topic for order-related messages.
 * This class defines a {@link NewTopic} bean that represents the "order-topic" in Kafka.
 * It uses Spring Kafka's {@link TopicBuilder} to create and configure the topic, ensuring
 * that it is available when the application starts. Kafka topics serve as channels for
 * message communication between producers and consumers.
 */
@Configuration
public class KafkaOrderTopicConfig {

    /**
     * Defines a Kafka topic named "order-topic".
     * This bean represents the "order-topic" in Kafka, which will be automatically created
     * and managed by Kafka. This topic can be used by producers to send messages and by consumers to
     * listen for order-related events.
     * @return a new {@link NewTopic} object representing the "order-topic".
     */
    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder
                .name("order-topic")
                .build();
    }
}
