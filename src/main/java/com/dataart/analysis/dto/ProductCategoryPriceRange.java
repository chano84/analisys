package com.dataart.analysis.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCategoryPriceRange {
    String productName;
    String category;
    BigDecimal minPrice;
    BigDecimal maxPrice;

    public ProductCategoryPriceRange(String productName, String category, BigDecimal minPrice, BigDecimal maxPrice) {
        this.productName = productName;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;

    }
}

