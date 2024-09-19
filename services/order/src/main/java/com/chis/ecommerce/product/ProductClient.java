package com.chis.ecommerce.product;

import com.chis.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service class responsible for interacting with the product microservice.
 * It sends purchase requests to the product service and retrieves the response.
 * This class uses Spring's {@link RestTemplate} to communicate via HTTP with the product service.
 */
@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    /**
     * Sends a purchase request to the product microservice and returns the list of purchased products.
     * @param requestBody The list of {@link PurchaseRequest} objects that contains the product ID and quantity to be purchased.
     * @return A list of {@link PurchaseResponse} objects that contain information about the purchased products.
     * @throws BusinessException if an error occurs while communicating with the product service, or if the response status indicates failure.
     */
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        // Set headers with content type
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        // Create HTTP request entity with body and headers
        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Specify the response type to handle the list of PurchaseResponse objects
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};

        // Make the POST request to the product microservice to purchase products
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );

        // Check if the response contains an error status
        if(responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }

        // Return the response body (list of purchased products)
        return responseEntity.getBody();
    }
}
