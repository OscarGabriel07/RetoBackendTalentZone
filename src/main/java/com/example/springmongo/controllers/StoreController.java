package com.example.springmongo.controllers;

import com.example.springmongo.models.BuyModel;
import com.example.springmongo.models.ProductModel;
import com.example.springmongo.services.BuyService;
import com.example.springmongo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    ProductService productService;
    @Autowired
    BuyService buyService;

    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductModel productModel){
        try {
            ProductModel product = productService.saveProduct(productModel);
            return new ResponseEntity<ProductModel>(product, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(){
        try {
            ArrayList<ProductModel> products = productService.getProducts();
            return new ResponseEntity<ArrayList<ProductModel>>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        try {
            ProductModel product = productService.getProductById(id);
            return new ResponseEntity<ProductModel>(product, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductModel productModel){
        try {
            ProductModel product = productService.saveProduct(productModel);
            return new ResponseEntity<ProductModel>(product, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        boolean ok = productService.deleteProductById(id);
        if(ok){
            return new ResponseEntity<String>("Se eliminó el producto con id " + id, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("No se puedo eliminar el producto con id " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<?> saveBuy(@RequestBody BuyModel buyModel){
        if(buyService.validateAvailability(buyModel) && buyService.validateMin(buyModel) && buyService.validateMax(buyModel)){
            try {
                BuyModel buy = buyService.saveBuy(buyModel);
                buyService.settlePurchase(buyModel);
                return new ResponseEntity<BuyModel>(buy, HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<String>("No es posible registrar la compra",
                    HttpStatus.CREATED);
        }
    }

    @GetMapping("/buys")
    public ResponseEntity<?> getBuys(){
        try {
            ArrayList<BuyModel> buys = buyService.getBuys();
            return new ResponseEntity<ArrayList<BuyModel>>(buys, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
