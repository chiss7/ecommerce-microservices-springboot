package com.chis.ecommerce.kafka;

import com.chis.ecommerce.customer.CustomerResponse;
import com.chis.ecommerce.order.PaymentMethod;
import com.chis.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents the order confirmation data that is sent as a Kafka message.
 * This data is used by the {@link OrderProducer} to send an order confirmation message
 * to a Kafka topic, which other services (such as billing or notification services) can
 * consume and process.
 * @param orderReference  A unique identifier for the order.
 * @param totalAmount     The total amount paid for the order.
 * @param paymentMethod   The method used for payment.
 * @param customer        Details of the customer who placed the order.
 * @param products        List of products purchased in the order.
 */
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
