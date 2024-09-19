package com.chis.ecommerce.kafka.payment;

import java.math.BigDecimal;

// The same properties as PaymentNotificationRequest in Payment Microservice
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
