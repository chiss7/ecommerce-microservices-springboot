package com.chis.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) // Lombok calls the equals() and hashCode() methods of the superclass in the methods generated for the subclass.
@Data
public class CustomerNotFoundException extends RuntimeException {
    private final String msg;
}
