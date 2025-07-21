package com.dataart.analysis.service;

import com.dataart.analysis.domain.Product;
import com.dataart.analysis.dto.PriceAvgDTO;
import com.dataart.analysis.dto.ProductDTO;
import com.dataart.analysis.dto.SummaryDTO;
import com.dataart.analysis.mapper.ProductMapper;
import com.dataart.analysis.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product save(ProductDTO productDTO){
        logger.info("ProductService.save() product DTO: {}", productDTO);
        Product product = productMapper.toEntity(productDTO);
        return this.productRepository.save(product);
    }

    public SummaryDTO getSummary(){
        logger.info("ProductService.getSummary()");
        List<Product> products = this.productRepository.findAll();
        SummaryDTO summaryDTO = new SummaryDTO();
        summaryDTO.setCategoryProductCount(this.productRepository.getTotalProductsByCategory());
        summaryDTO.setSiteCategoryAveragePrice(this.productRepository.getAveragePriceBySiteAndCategory());
        summaryDTO.setProductCategoryPriceRange(this.productRepository.getPriceRangeByProductAndCategory());
        return summaryDTO;
    }

    public List<ProductDTO> getTopProducts(){
        logger.info("ProductService.getTopProducts()");
        List<Product> products = this.productRepository.findTopProductsAvailableOrdered();
        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    public List<PriceAvgDTO> getTrend(String productName){
        logger.info("ProductService.getTrend() product name: {}", productName);
        return this.productRepository.getPriceAvgByDay(productName);
    }

    public List<ProductDTO> getAll(){
        return this.productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }
}
