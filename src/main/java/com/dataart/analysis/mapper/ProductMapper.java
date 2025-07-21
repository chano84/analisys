package com.dataart.analysis.mapper;

import com.dataart.analysis.domain.Product;
import com.dataart.analysis.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product entity) {
        if (entity == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setProductId(entity.getProductId());
        dto.setProductName(entity.getProductName());
        dto.setPrice(entity.getPrice());
        dto.setSiteName(entity.getSiteName());
        dto.setCategory(entity.getCategory());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        Product entity = new Product();
        entity.setProductId(dto.getProductId());
        entity.setProductName(dto.getProductName());
        entity.setPrice(dto.getPrice());
        entity.setSiteName(dto.getSiteName());
        entity.setCategory(dto.getCategory());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}
