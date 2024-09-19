package com.chis.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents the data that a client sends to the server when requesting a client-related operation.
 * Ensures that the data is immutable once created, which is a best practice in handling requests to avoid unwanted side effects.
 * @param productId
 * @param quantity
 */
public record PurchaseRequest(
        @NotNull(message = "Product is required")
        Integer productId,
        @Positive(message = "Quantity should be positive")
        double quantity
) {
}
