package com.spring.mongocrud.controllers;

import com.spring.mongocrud.exceptions.AlreadyPresentException;
import com.spring.mongocrud.models.Product;
import com.spring.mongocrud.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class ProductController {

    @Autowired private ProductService productService;

    @GetMapping("products/getAllProducts")
    public List<Product> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return productList;
    }

    @GetMapping("/products")
    public List<Product> getActiveProducts() {
        List<Product> productList = productService.getActiveProducts();
        //System.out.println("productList = " + productList);
        return productList;
    }

    @PostMapping("/products/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
        } catch (AlreadyPresentException ex) {
            return ResponseEntity.ok("Id already exist in the database...!");
        }
        return ResponseEntity.ok("Product Added");
    }

    @PutMapping("/products/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok("Product Updated");
    }

    @DeleteMapping("products/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with id:" + id + " Deleted Successfully");
    }

    @PutMapping("products/softDelete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable String id) {
        productService.softDelete(id);
        return ResponseEntity.ok("Soft Deleted product with id " + id);
    }


}
