package com.example.springmongo.services;

import com.example.springmongo.models.ProductModel;
import com.example.springmongo.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    void saveProduct() {

        ProductModel product = new ProductModel(9L, "Samsung Galaxy S9", 280, true, 2, 8);

        when(productRepository.save(product)).thenReturn(product);

        assertEquals(productService.saveProduct(product).getInInventory(), 280);
        assertTrue(productService.saveProduct(product).getEnabled());

    }

    @Test
    void getProducts() {

        ProductModel product = new ProductModel(8L, "Xiaomi MI 9 Lite", 500, true, 15, 30);
        ArrayList<ProductModel> products = new ArrayList<>();
        products.add(product);

        when(productRepository.findAll()).thenReturn(products);
        assertEquals(1, productService.getProducts().size());

    }

    @Test
    void updateProduct() {
        ProductModel productModified = new ProductModel
                (9L, "Motorola One XS", 1290, true, 10, 25);

        when(productRepository.save(productModified)).thenReturn(productModified);

        assertEquals(productService.updateProduct(productModified).getMax(), 25);
        assertNotEquals(productService.updateProduct(productModified).getInInventory(), 1400);
    }

    @Test
    void deleteProductById() {

        Long id = 10L;

        doNothing().when(productRepository).deleteById(id);

        assertEquals(productService.deleteProductById(id), true);


    }
}