package com.spring.mongocrud.services;

import com.spring.mongocrud.exceptions.AlreadyPresentException;
import com.spring.mongocrud.models.Product;
import com.spring.mongocrud.validator.ProductRequestResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> getAllProducts();

    public List<Product> getActiveProducts();

    public Product addProduct(ProductRequestResponse productRequestResponse) throws AlreadyPresentException;

    public Product updateProduct(ProductRequestResponse productRequestResponse);

    public Product deleteProduct(String id);

    public Product softDelete(String id);
}
