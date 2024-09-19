package com.chis.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    /**
     * Converts a CustomerRequest object (which comes from an HTTP request) to a Customer object (which is stored in the database).
     * @param request
     * @return Customer
     */
    public Customer toCustomer(CustomerRequest request) {
        if (request == null) return null;
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    /**
     * Converts a Customer object (retrieved from the database) to a CustomerResponse object (sent as an HTTP response).
     * @param customer
     * @return CustomerResponse
     */
    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
