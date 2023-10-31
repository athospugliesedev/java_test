package com.farmacia.controller;

import com.farmacia.model.Product;
import com.farmacia.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(19.99);

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(product, UriComponentsBuilder.newInstance());

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Test Product", response.getBody().getName());
        assertEquals(19.99, response.getBody().getPrice());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList add(new Product());

        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productController.getAllProducts();

        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateProduct() {
        Long id = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(id);
        existingProduct.setName("Produto cadastrado!");
        existingProduct.setPrice(25.99);

        Product updatedProduct = new Product();
        updatedProduct.setId(id);
        updatedProduct.setName("Produto postado!");
        updatedProduct.setPrice(29.99);

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(id, updatedProduct);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Produto postado!", response.getBody().getName());
        assertEquals(29.99, response.getBody().getPrice());
    }

    @Test
    public void testUpdateProduct_NotFound() {
        Long id = 1L;
        Product updatedProduct = new Product();

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productController.updateProduct(id, updatedProduct));
    }

    @Test
    public void testDeleteProduct() {
        Long id = 1L;

        ResponseEntity<Void> response = productController.deleteProduct(id);

        assertEquals(204, response.getStatusCodeValue());
    }
}
