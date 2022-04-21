package com.spring.mongocrud.services;

import com.spring.mongocrud.exceptions.AlreadyPresentException;
import com.spring.mongocrud.models.Product;
import com.spring.mongocrud.models.Status;
import com.spring.mongocrud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return new ArrayList<>(productRepository.findAll());
    }

    public List<Product> getActiveProducts() {
        ArrayList<Product> productsList = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            if (product.getStatus() != Status.DELETED) {
                productsList.add(product);
            }
        });
        return productsList;
    }

    public Product addProduct(Product product) throws AlreadyPresentException {
        Product isExist=this.productRepository.findById(product.getId()).orElse(null);
        if(isExist!=null)
        {
            throw new AlreadyPresentException();
        }
        product.setInsertDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        product.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return productRepository.insert(product);
    }

    public Product updateProduct(Product product) {
        product=this.productRepository.findById(product.getId()).orElse(null);
        product.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return productRepository.save(product);
    }

    public Product deleteProduct(String id) {
        Product deletedProduct = productRepository.findById(id).orElse(null);
        productRepository.deleteById(id);
        return deletedProduct;
    }

    public Product softDelete(String id) {
        Product documentToUpdate = productRepository.findById(id).orElse(null);
        documentToUpdate.setStatus(Status.DELETED);
        return productRepository.save(documentToUpdate);
    }
}
