package com.dataart.analysis.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductTopDTO {

    String category;
    String productName;
    String siteName;
    BigDecimal price;

    public ProductTopDTO(String category, String productName, String siteName ,  BigDecimal price) {
        this.category = category;
        this.productName = productName;
        this.price = price;
        this.siteName = siteName;
    }

}
