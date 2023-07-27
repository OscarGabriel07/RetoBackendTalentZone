package com.example.springmongo.services;

import com.example.springmongo.models.BuyModel;
import com.example.springmongo.models.Order;
import com.example.springmongo.models.ProductModel;
import com.example.springmongo.repositories.BuyRepository;
import com.example.springmongo.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BuyServiceTest {

    @Mock
    BuyRepository buyRepository;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    BuyService buyService;
    @InjectMocks
    ProductService productService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveBuy() {

        ArrayList<Order> products = new ArrayList<>();
        products.add(new Order(1L, 10));
        products.add(new Order(2L, 20));

        BuyModel buy = new BuyModel
                (6L, "27/07/2023", "1", "1022358965", "Miguel Suarez", products);

        when(buyRepository.save(buy)).thenReturn(buy);

        assertEquals(buyService.saveBuy(buy).getProducts().get(0).getQuantity(), 10);
        assertNotNull(buyService.saveBuy(buy).getProducts());
    }

    @Test
    void getBuys() {

        ArrayList<Order> products1 = new ArrayList<>();
        products1.add(new Order(1L, 10));
        products1.add(new Order(2L, 20));

        BuyModel buy1 = new BuyModel
                (6L, "27/07/2023", "1", "1022358965", "Miguel Suarez", products1);

        ArrayList<Order> products2 = new ArrayList<>();
        products2.add(new Order(3L, 30));
        products2.add(new Order(4L, 40));

        BuyModel buy2 = new BuyModel
                (7L, "26/07/2023", "1", "50358468", "Ana Gomez", products2);


        ArrayList<BuyModel> buys = new ArrayList<>();
        buys.add(buy1);
        buys.add(buy2);

        when(buyRepository.findAll()).thenReturn(buys);
        assertNotNull(buyService.getBuys());
        assertEquals(buyService.getBuys().size(), 2);
    }

    @Test
    void validateAvailability() {

        ProductModel product1 = new ProductModel(1L, "Samsung Galaxy S22 Plus", 955, true, 10, 15);
        ProductModel product2 = new ProductModel(2L, "Samsung Galaxy S20 FE", 1250, true, 15, 30);

        ArrayList<ProductModel> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        ArrayList<Order> productsBuy = new ArrayList<>();
        productsBuy.add(new Order(1L, 10));
        productsBuy.add(new Order(2L, 20));

        BuyModel buy = new BuyModel
                (6L, "27/07/2023", "1", "1022358965", "Miguel Suarez", productsBuy);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));

        assertTrue(buyService.validateAvailability(buy));

    }

    @Test
    void validateMin() {
        ProductModel product1 = new ProductModel(1L, "Samsung Galaxy S22 Plus", 955, true, 10, 15);
        ProductModel product2 = new ProductModel(2L, "Samsung Galaxy S20 FE", 1250, true, 15, 30);

        ArrayList<ProductModel> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        ArrayList<Order> productsBuy = new ArrayList<>();
        productsBuy.add(new Order(1L, 5));
        productsBuy.add(new Order(2L, 8));

        BuyModel buy = new BuyModel
                (6L, "27/07/2023", "1", "1022358965", "Miguel Suarez", productsBuy);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));

        assertFalse(buyService.validateMin(buy));
    }

    @Test
    void validateMax() {
        ProductModel product1 = new ProductModel(1L, "Samsung Galaxy S22 Plus", 955, true, 10, 15);
        ProductModel product2 = new ProductModel(2L, "Samsung Galaxy S20 FE", 1250, true, 15, 30);

        ArrayList<ProductModel> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        ArrayList<Order> productsBuy = new ArrayList<>();
        productsBuy.add(new Order(1L, 14));
        productsBuy.add(new Order(2L, 20));

        BuyModel buy = new BuyModel
                (6L, "27/07/2023", "1", "1022358965", "Miguel Suarez", productsBuy);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));

        assertTrue(buyService.validateMax(buy));
    }

    @Test
    void settlePurchase() {

        ProductModel product1 = new ProductModel(1L, "Samsung Galaxy S22 Plus", 955, true, 10, 15);
        ProductModel product1Modified = new ProductModel(1L, "Samsung Galaxy S22 Plus", 941, true, 10, 15);

        ArrayList<ProductModel> products = new ArrayList<>();
        products.add(product1);

        ArrayList<Order> productsBuy = new ArrayList<>();
        productsBuy.add(new Order(1L, 14));

        BuyModel buy = new BuyModel
                (6L, "27/07/2023", "1", "1022358965", "Miguel Suarez", productsBuy);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productService.updateProduct(product1)).thenReturn(product1);

        assertEquals(product1Modified.getInInventory(), 941);

    }
}