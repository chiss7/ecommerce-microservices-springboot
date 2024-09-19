package com.chis.ecommerce.order;

import java.math.BigDecimal;

/**
 * Represents the data that the server returns to the client in response to a client-related operation.
 * Ensures that the data is immutable once created, which is a best practice in handling requests to avoid unwanted side effects.
 * @param id
 * @param reference
 * @param totalAmount
 * @param paymentMethod
 * @param customerId
 */
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
