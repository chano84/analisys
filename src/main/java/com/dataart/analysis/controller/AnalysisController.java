package com.dataart.analysis.controller;

import com.dataart.analysis.dto.PriceAvgDTO;
import com.dataart.analysis.dto.ProductDTO;
import com.dataart.analysis.dto.SummaryDTO;
import com.dataart.analysis.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    private final ProductService productService;

    public AnalysisController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllMyEntities() {
        logger.info("Received request to get all entities");
        List<ProductDTO> entities = productService.getAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/summary")
    public ResponseEntity<SummaryDTO> getSummary() {
        logger.info("Received request to getSummary");
        SummaryDTO summaryDTO = productService.getSummary();
        return ResponseEntity.ok(summaryDTO);

    }

    @GetMapping("/top-products")
    public ResponseEntity<List<ProductDTO>> getTopProducts() {
        logger.info("Received request to getTopProducts");
        List<ProductDTO> entities = productService.getTopProducts();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/trend/{productName}")
    public ResponseEntity<List<PriceAvgDTO>> getTrend(@PathVariable String productName) {
        logger.info("Received request to get getTrend {}",productName);
        List<PriceAvgDTO> entities = productService.getTrend(productName);
        return ResponseEntity.ok(entities);
    }
}
