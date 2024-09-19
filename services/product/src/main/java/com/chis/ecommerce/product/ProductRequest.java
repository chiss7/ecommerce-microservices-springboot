package com.chis.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * Represents the data that a client sends to the server when requesting a client-related operation.
 * Ensures that the data is immutable once created, which is a best practice in handling requests to avoid unwanted side effects.
 * @param id
 * @param name
 * @param description
 * @param availableQuantity
 * @param price
 * @param categoryId
 */
public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Available quantity should be positive")
        Double availableQuantity,
        @Positive(message = "Available quantity should be positive")
        BigDecimal price,
        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}
