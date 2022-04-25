package com.spring.mongocrud.repositories;

import com.spring.mongocrud.models.Product;
import com.spring.mongocrud.models.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Component
public interface ProductRepository extends MongoRepository<Product, String> {

    //getting only active products
    @Query(
            value = "{}",
            fields = "{insertDate:0,updateDate: 0}"
    )
    public List<Product> findActiveProducts();
}
