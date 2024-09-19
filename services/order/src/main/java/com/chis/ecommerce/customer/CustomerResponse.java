package com.chis.ecommerce.customer;

/**
 * Represents the data that the server returns to the client in response to a client-related operation.
 * Ensures that the data is immutable once created, which is a best practice in handling requests to avoid unwanted side effects.
 * @param id
 * @param firstname
 * @param lastname
 * @param email
 */
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
