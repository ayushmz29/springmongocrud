package com.spring.mongocrud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {
    @Id
    //@Indexed(unique = true)
    private String id;
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
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String insertDate;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String updateDate;
    //private LocalDateTime insertDate;
    //private LocalDateTime updateDate;

}
