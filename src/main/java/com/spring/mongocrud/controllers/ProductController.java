package com.spring.mongocrud.controllers;

import com.spring.mongocrud.exceptions.AlreadyPresentException;
import com.spring.mongocrud.models.Product;
import com.spring.mongocrud.services.ProductService;
import com.spring.mongocrud.validator.ProductRequestResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class ProductController {

    @Autowired private ProductService productService;

    @GetMapping("products/getAllProducts")
    public ResponseEntity<?> getAllProducts() {
        List<Product> productList = productService.getAllProducts();


        return ResponseEntity.ok(productList);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getActiveProducts() {
        List<Product> productList = productService.getActiveProducts();
        //System.out.println("productList = " + productList);
        ArrayList<ProductRequestResponse> productRequestResponseArrayList = new ArrayList<>();
        productList.forEach(product -> {
            if (!product.getStatus().toString().equalsIgnoreCase("deleted"))
                productRequestResponseArrayList.add(new ProductRequestResponse(product));
        });
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/products/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestResponse productRequestResponse) {
        Product newProduct;
        try {
            newProduct = productService.addProduct(productRequestResponse);
        } catch (AlreadyPresentException ex) {
            return ResponseEntity.internalServerError().body("Id already exist in the database...!");
        }
        return ResponseEntity.of(Optional.of(newProduct));
    }

    @PutMapping("/products/update")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductRequestResponse productRequestResponse) {
        Product updatedProduct = productService.updateProduct(productRequestResponse);
        productRequestResponse = new ProductRequestResponse(updatedProduct);
        return ResponseEntity.of(Optional.of(productRequestResponse));
    }

    @DeleteMapping("products/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        if (id != null) {
            Product product = productService.deleteProduct(id);
            ProductRequestResponse productRequestResponse = new ProductRequestResponse(product);
            return ResponseEntity.of(Optional.of(productRequestResponse));
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @PutMapping("products/softDelete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable String id) {
        if (id != null) {
            Product product = productService.softDelete(id);
            ProductRequestResponse productRequestResponse = new ProductRequestResponse(product);
            return ResponseEntity.ok(productRequestResponse);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            errors.put("Status", "Bad Request(400)");
        });
        return errors;
    }

}
