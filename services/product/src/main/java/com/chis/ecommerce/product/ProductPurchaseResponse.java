package com.chis.ecommerce.product;

import java.math.BigDecimal;

/**
 * Represents the data that the server returns to the client in response to a client-related operation.
 * Ensures that the data is immutable once created, which is a best practice in handling requests to avoid unwanted side effects.
 * @param productId
 * @param name
 * @param description
 * @param price
 * @param quantity
 */
public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
