package com.example.springmongo.services;

import com.example.springmongo.models.ProductModel;
import com.example.springmongo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductModel saveProduct(ProductModel productModel){
        if(productModel.getInInventory() <= 0){
            productModel.setEnabled(false);
        }
        return productRepository.save(productModel);
    }

    public ArrayList<ProductModel> getProducts(){
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    public ProductModel getProductById(Long id){
        return productRepository.findById(id).get();
    }

    public ProductModel updateProduct(ProductModel productModel){
        if(productModel.getInInventory() <= 0){
            productModel.setEnabled(false);
        }
        return productRepository.save(productModel);
    }

    public Boolean deleteProductById(Long id){
        try{
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
