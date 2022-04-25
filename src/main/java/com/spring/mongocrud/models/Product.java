package com.spring.mongocrud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {
    @Id
    //@Indexed(unique = true)
    private String id;
    private String sku;
    private String name;
    private String description;
    private String brand;
    private String color;
    private String weight;
    private List<String> internalCategories;
    private String productType;
    private String mainImageUrl;

    //note, enum 'Status'
    private Status status;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore private String insertDate;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore private String updateDate;

}
