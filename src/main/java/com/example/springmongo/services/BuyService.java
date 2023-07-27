package com.example.springmongo.services;

import com.example.springmongo.models.BuyModel;
import com.example.springmongo.models.Order;
import com.example.springmongo.models.ProductModel;
import com.example.springmongo.repositories.BuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BuyService {

    @Autowired
    BuyRepository buyRepository;
    @Autowired
    ProductService productService;

    public BuyModel saveBuy(BuyModel buyModel){
        return buyRepository.save(buyModel);
    }

    public ArrayList<BuyModel> getBuys(){
        return (ArrayList<BuyModel>) buyRepository.findAll();
    }

    public Boolean validateAvailability(BuyModel buyModel){
        ArrayList<Order> products = buyModel.getProducts();
        boolean validation = true;
        for (Order product: products) {
            ProductModel productModel = productService.getProductById(product.getIdProduct());
            if (productModel.getInInventory() < product.getQuantity()){
                validation = false;
            }
        }
        return validation;
    }

    public Boolean validateMin(BuyModel buyModel){
        ArrayList<Order> products = buyModel.getProducts();
        boolean validation = true;
        for (Order product: products) {
            ProductModel productModel = productService.getProductById(product.getIdProduct());
            if (product.getQuantity() < productModel.getMin()){
                validation = false;
            }
        }
        return validation;
    }

    public Boolean validateMax(BuyModel buyModel){
        ArrayList<Order> products = buyModel.getProducts();
        boolean validation = true;
        for (Order product: products) {
            ProductModel productModel = productService.getProductById(product.getIdProduct());
            if (product.getQuantity() > productModel.getMax()){
                validation = false;
            }
        }
        return validation;
    }

    public void settlePurchase(BuyModel buyModel){
        ArrayList<Order> products = buyModel.getProducts();
        for (Order product: products) {
            ProductModel productModel = productService.getProductById(product.getIdProduct());
            productModel.setInInventory(productModel.getInInventory() - product.getQuantity());
            productService.updateProduct(productModel);
        }
    }

}
