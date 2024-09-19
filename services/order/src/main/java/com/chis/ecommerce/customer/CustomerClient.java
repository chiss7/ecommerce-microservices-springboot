package com.chis.ecommerce.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Feign client interface for interacting with the customer microservice.
 * This interface defines the contract for the order microservice to retrieve customer information
 * from the customer service. It leverages Spring Cloud OpenFeign to handle the HTTP communication
 * between the microservices in a declarative way.
 * The base URL for the customer service is injected from the application configuration.
 */
@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    /**
     * Retrieves a customer's details based on their customer ID.
     * Sends a GET request to the customer microservice using the provided customer ID.
     * @param customerId The unique identifier of the customer whose information is being requested.
     * @return An {@link Optional<CustomerResponse>} containing customer details, if found.
     */
    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}
