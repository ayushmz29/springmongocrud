package com.spring.mongocrud.services.impl;

import com.spring.mongocrud.exceptions.AlreadyPresentException;
import com.spring.mongocrud.models.Product;
import com.spring.mongocrud.models.Status;
import com.spring.mongocrud.repositories.ProductRepository;
import com.spring.mongocrud.services.ProductService;
import com.spring.mongocrud.validator.ProductRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class productServiceImpl implements ProductService{
    @Autowired private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return new ArrayList<>(productRepository.findAll());
    }

    public List<Product> getActiveProducts() {
        ArrayList<Product> productsList = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            Status productStatus = product.getStatus();
            if (productStatus != Status.DELETED && productStatus != Status.INACTIVE) {
                productsList.add(product);
            }
        });
        return productsList;

        /*List<Product> productsList = productRepository.findActiveProducts();
        return productsList;*/
    }

    public Product addProduct(ProductRequestResponse productRequestResponse) throws AlreadyPresentException {
        Product isExist = this.productRepository.findById(productRequestResponse.getId()).orElse(null);
        if (isExist != null) {
            throw new AlreadyPresentException();
        }
        Product product = productRequestResponse.fillProductByProductRequestResponse();
        product.setInsertDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        product.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return productRepository.insert(product);
    }

    public Product updateProduct(ProductRequestResponse productRequestResponse) {
        Product product = this.productRepository.findById(productRequestResponse.getId()).orElse(null);
        if(product!=null)
        {
            product=productRequestResponse.fillProductByProductRequestResponse(product);
            product.setInsertDate(product.getInsertDate());
            product.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            productRepository.save(product);
        }
        return product;
    }

    public Product deleteProduct(String id) {
        Product toBeDeletedProduct = productRepository.findById(id).orElse(null);
        if(toBeDeletedProduct != null)
        {
            productRepository.deleteById(id);
        }
        return toBeDeletedProduct;
    }

    public Product softDelete(String id) {
        Product documentToUpdate = productRepository.findById(id).orElse(null);
        documentToUpdate.setStatus(Status.DELETED);
        return productRepository.save(documentToUpdate);
    }

}
