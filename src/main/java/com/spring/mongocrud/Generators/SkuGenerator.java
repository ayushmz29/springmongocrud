package com.spring.mongocrud.Generators;

public class SkuGenerator {
    public static String getSku(String name, String brand, String color, String productType) {
        String skuValue = name.substring(0, 3) +
                          "-" +
                          brand.substring(0, 3) +
                          "-" +
                          color.substring(0, 3) +
                          "-" +
                          productType.substring(0, 3);

        return skuValue;
    }
}
