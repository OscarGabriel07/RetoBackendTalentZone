package com.example.springmongo.repositories;


import com.example.springmongo.models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductModel, Long> {
}
