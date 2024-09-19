package com.chis.ecommerce.payment;

import com.chis.ecommerce.customer.CustomerResponse;
import com.chis.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

/**
 * Represents the data that a client sends to the server when requesting a client-related operation.
 * Ensures that the data is immutable once created, which is a best practice in handling requests to avoid unwanted side effects.
 * @param amount
 * @param paymentMethod
 * @param orderId
 * @param orderReference
 * @param customer
 */
public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
