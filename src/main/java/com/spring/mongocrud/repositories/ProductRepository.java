package com.spring.mongocrud.repositories;

import com.spring.mongocrud.models.Product;
import com.spring.mongocrud.models.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
