package com.farmacia.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.farmacia.model.Product;
import com.farmacia.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts() {
        // Simulate the behavior of the productRepository to return a list of products
        List<Product> products = List.of(new Product(1L, "Product A", 10.0));

        Mockito.when(productRepository.findAll()).thenReturn(products);

        // Call the service method and assert the result
        List<Product> result = productService.getAllProducts();

        // Add your assertions here
        // For example, you can use JUnit assertions to check the result
        // For example: assertEquals(products.size(), result.size());
    }

    @Test
    public void testGetProductById() {
        // Simulate the behavior of the productRepository to return a product
        long productId = 1L;
        Product product = new Product(productId, "Product A", 10.0);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Call the service method and assert the result
        Optional<Product> result = productService.getProductById(productId);

        // Add your assertions here
        // For example, you can use JUnit assertions to check the result
        // For example: assertEquals(product, result.orElse(null));
    }

    // Add more test methods for other service methods

}
