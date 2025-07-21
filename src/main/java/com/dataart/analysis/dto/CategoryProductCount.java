package com.dataart.analysis.dto;

import lombok.Data;

@Data
public class CategoryProductCount {

    String  category;
    Long count;

    public CategoryProductCount(String category, Long count) {
        this.category = category;
        this.count = count;
    }
}
