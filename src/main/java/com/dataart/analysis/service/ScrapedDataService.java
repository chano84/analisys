package com.dataart.analysis.service;

import com.dataart.analysis.dto.ProductDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ScrapedDataService {

    private static final Logger logger = LoggerFactory.getLogger(ScrapedDataService.class);

    private final Validator validator;

    private final ProductService productService;

    public ScrapedDataService(Validator validator, ProductService productService) {
        this.validator = validator;
        this.productService = productService;
    }

    @Async
    public void upload(List<ProductDTO> productDTOList){
        int row = 1;
        logger.info("ScrapedDataService upload started, count: " + productDTOList.size());
        for (ProductDTO product : productDTOList) {
            Set<ConstraintViolation<ProductDTO>> violaciones = validator.validate(product);
            if (!violaciones.isEmpty()) {
                for (ConstraintViolation<?> violacion : violaciones) {
                    logger.error("Row " + row + ": " + violacion.getPropertyPath() + " " + violacion.getMessage());
                }
            }else{
                this.productService.save(product);
            }
            row++;
        }
    }


}
