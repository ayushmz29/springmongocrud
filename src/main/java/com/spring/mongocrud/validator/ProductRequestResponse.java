package com.spring.mongocrud.validator;

import com.spring.mongocrud.Generators.SkuGenerator;
import com.spring.mongocrud.models.Product;
import com.spring.mongocrud.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestResponse implements Serializable {
    @NotEmpty(message = "Id should not be left blank or null...")
    @PositiveOrZero(message = "Id can not be negative...")
    private String id;

    private String sku;

    @NotEmpty(message = "Name must not be left blank or null...") private String name;

    private String description;

    @NotNull(message = "Brand should not be left blank...") private String brand;

    @NotEmpty(message = "Color must not be empty...") private String color;

    @NotEmpty(message = "Weight cannot be Empty...") private String weight;

    private Status status;

    private List<@NotEmpty String> internalCategories;

    private String productType;

    private String mainImageUrl;

    //constructor
    public ProductRequestResponse(Product product) {
        if (product != null) {
            this.id                 = product.getId();
            this.sku                = product.getSku();
            this.brand              = product.getBrand();
            this.color              = product.getColor();
            this.description        = product.getDescription();
            this.internalCategories = product.getInternalCategories();
            this.name               = product.getName();
            this.productType        = product.getProductType();
            this.status             = product.getStatus();
            this.weight             = product.getWeight();
            this.mainImageUrl       = product.getMainImageUrl();
        }
    }

    //methods
    public Product fillProductByProductRequestResponse() {

        Product product = new Product();
        String generatedSku = SkuGenerator.getSku(this.getName(), this.getBrand(), this.getColor(), this.productType);

        product.setId(this.getId());
        product.setSku(generatedSku);
        product.setBrand(this.getBrand());
        product.setColor(this.getColor());
        product.setDescription(this.getDescription());
        product.setInternalCategories(this.getInternalCategories());
        product.setName(this.getName());
        product.setProductType(this.getProductType());
        product.setStatus(this.getStatus());
        product.setWeight(this.getWeight());
        product.setMainImageUrl(this.getMainImageUrl());

        return product;
    }

    public Product fillProductByProductRequestResponse(Product product) {
        String generatedSku = SkuGenerator.getSku(this.getName(), this.getBrand(), this.getColor(), this.productType);

        product.setId(this.getId());
        product.setSku(generatedSku);
        product.setBrand(this.getBrand());
        product.setColor(this.getColor());
        product.setDescription(this.getDescription());
        product.setInternalCategories(this.getInternalCategories());
        product.setName(this.getName());
        product.setProductType(this.getProductType());
        product.setStatus(this.getStatus());
        product.setWeight(this.getWeight());
        product.setMainImageUrl(this.getMainImageUrl());
        product.setInsertDate(product.getInsertDate());
        product.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return product;
    }
}
