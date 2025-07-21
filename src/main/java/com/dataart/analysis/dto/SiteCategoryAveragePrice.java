package com.dataart.analysis.dto;

import lombok.Data;

@Data
public class SiteCategoryAveragePrice {
    String siteName;
    String category;
    Double price;

    public SiteCategoryAveragePrice(String siteName, String category, Double price) {
        this.siteName = siteName;
        this.category = category;
        this.price = price;
    }
}
