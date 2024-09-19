package com.chis.ecommerce.product;

import java.math.BigDecimal;

/**
 * Represents the data that the server returns to the client in response to a client-related operation.
 * Ensures that the data is immutable once created, which is a best practice in handling requests to avoid unwanted side effects.
 * @param id
 * @param name
 * @param description
 * @param availableQuantity
 * @param price
 * @param categoryId
 * @param categoryName
 * @param categoryDescription
 */
public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
