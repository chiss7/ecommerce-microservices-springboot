package com.chis.ecommerce.order;

import com.chis.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents the data that a client sends to the server when requesting a client-related operation.
 * Ensures that the data is immutable once created, which is a best practice in handling requests to avoid unwanted side effects.
 * @param id
 * @param reference
 * @param totalAmount
 * @param paymentMethod
 * @param customerId
 * @param products
 */
public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal totalAmount,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer is required")
        @NotEmpty(message = "Customer is required")
        @NotBlank(message = "Customer is required")
        String customerId,
        @NotEmpty(message = "You should at least purchased one product")
        List<PurchaseRequest> products
) {
}
