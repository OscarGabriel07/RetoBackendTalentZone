package com.example.springmongo.repositories;

import com.example.springmongo.models.BuyModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyRepository extends MongoRepository<BuyModel, Long> {
}
