package com.dataart.analysis.repository;

import com.dataart.analysis.domain.Product;
import com.dataart.analysis.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT new com.dataart.analysis.dto.CategoryProductCount(p.category , COUNT(p) ) FROM Product p GROUP BY p.category")
    List<CategoryProductCount> getTotalProductsByCategory();

    @Query("SELECT new com.dataart.analysis.dto.SiteCategoryAveragePrice(p.siteName , p.category, AVG(p.price) ) FROM Product p GROUP BY p.siteName, p.category")
    List<SiteCategoryAveragePrice> getAveragePriceBySiteAndCategory();

    @Query("SELECT new com.dataart.analysis.dto.ProductCategoryPriceRange(p.productName, p.category, MIN(p.price), MAX(p.price) ) FROM Product p GROUP BY p.productName, p.category")
    List<ProductCategoryPriceRange> getPriceRangeByProductAndCategory();

    @Query("""
    SELECT p
    FROM Product p
    WHERE p.available = true
    ORDER BY p.category, p.price DESC
    """)
    List<Product> findTopProductsAvailableOrdered();


    /**
     * RESOLVER EL GRUOUP BY , HAY QUE TRUNCAR LA FECHA
     * @param productName
     * @return
     */
    @Query( """
    SELECT new com.dataart.analysis.dto.PriceAvgDTO(
        p.siteName ,
        p.createdAt,
        AVG(p.price) )
    FROM Product p
    WHERE p.productName = :productName
    GROUP BY p.siteName, p.createdAt
    ORDER BY p.siteName, p.createdAt
    """)
    List<PriceAvgDTO> getPriceAvgByDay(@Param("productName")  String productName);

}
