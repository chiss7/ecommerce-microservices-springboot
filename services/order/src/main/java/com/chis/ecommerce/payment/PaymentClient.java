package com.chis.ecommerce.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client interface for communicating with the payment microservice.
 * This interface defines the contract for the order microservice to request payments for orders
 * from the payment service. It uses Spring Cloud OpenFeign to simplify HTTP communication
 * between microservices.
 * The base URL for the payment service is injected from the application configuration.
 */
@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    /**
     * Sends a payment request to the payment microservice.
     * @param request The {@link PaymentRequest} object containing payment details, including the amount,
     *                payment method, order information, and customer details.
     * @return An {@link Integer} representing the payment transaction ID.
     */
    @PostMapping
    Integer requestOrderPayment(@RequestBody PaymentRequest request);
}
