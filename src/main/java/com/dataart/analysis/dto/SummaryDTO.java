package com.dataart.analysis.dto;

import lombok.Data;

import java.util.List;

@Data
public class SummaryDTO {

    List<CategoryProductCount> categoryProductCount;
    List<ProductCategoryPriceRange> productCategoryPriceRange;
    List<SiteCategoryAveragePrice> siteCategoryAveragePrice;

}
