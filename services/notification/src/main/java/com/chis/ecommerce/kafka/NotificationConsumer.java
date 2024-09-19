package com.chis.ecommerce.kafka;

import com.chis.ecommerce.email.EmailService;
import com.chis.ecommerce.kafka.order.OrderConfirmation;
import com.chis.ecommerce.kafka.payment.PaymentConfirmation;
import com.chis.ecommerce.notification.Notification;
import com.chis.ecommerce.notification.NotificationRepository;
import com.chis.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * NotificationConsumer listens to Kafka topics for payment and order events.
 * It consumes messages from these topics, saves notifications to the database,
 * and triggers email notifications to customers based on the event type.
 * This service interacts with NotificationRepository to persist notification data,
 * and with EmailService to send email alerts.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    /**
     * Consumes messages from the 'payment-topic' Kafka topic.
     * Processes payment confirmation data, saves it as a notification, and
     * sends a payment success email to the customer.
     * @param paymentConfirmation the data related to the payment confirmation event
     * @throws MessagingException if there's an error sending the email
     */
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic Topic: %s", paymentConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        // send email
        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    /**
     * Consumes messages from the 'order-topic' Kafka topic.
     * Processes order confirmation data, saves it as a notification, and
     * sends an order confirmation email to the customer.
     * @param orderConfirmation the data related to the order confirmation event
     * @throws MessagingException if there's an error sending the email
     */
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic: %s", orderConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // send email
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
