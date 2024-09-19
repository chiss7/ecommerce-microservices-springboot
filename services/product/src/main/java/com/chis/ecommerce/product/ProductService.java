package com.chis.ecommerce.product;

import com.chis.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {
        var product = productRepository.save(mapper.toProduct(request));
        return product.getId();
    }

    /**
     * Processes a list of product purchase requests, updating the available quantity of each product
     * in the repository and returning the details of the purchased products.
     *
     * @param request A list of {@link ProductPurchaseRequest} objects, each containing the product ID
     *                and the quantity to be purchased.
     *
     * @return A list of {@link ProductPurchaseResponse} objects representing the purchased products
     *         along with the quantities purchased.
     *
     * @throws ProductPurchaseException if:
     *         - Any of the requested product IDs do not exist in the repository.
     *         - The requested quantity for any product exceeds the available stock.
     */
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        // Check if all requested products exist
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists.");
        }

        // Sort requests by product ID to match the order of stored products
        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        // Process each product request
        for (int i = 0; i< storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            // Validate if there's enough stock for the requested quantity
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with id: " + productRequest.productId());
            }
            // Update product's available quantity
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);

            // Map product and quantity to response and add to the list
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
