package com.dataart.analysis.controller;

import com.dataart.analysis.dto.ProductDTO;
import com.dataart.analysis.service.ScrapedDataService;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/scraped-data")
public class ScrapedDataController {

    private static final Logger logger = LoggerFactory.getLogger(ScrapedDataController.class);

    private ScrapedDataService scrapedDataService;

    public ScrapedDataController(Validator validator, ScrapedDataService scrapedDataService) {
        this.scrapedDataService = scrapedDataService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> crearProductoDesdeCSV(@RequestParam("file") MultipartFile file) {
            List<String> errores = new ArrayList<>();
            try {
                List<ProductDTO> products = new CsvToBeanBuilder<ProductDTO>(
                        new InputStreamReader(file.getInputStream()))
                        .withType(ProductDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build()
                        .parse();
                this.scrapedDataService.upload(products);
                if (!errores.isEmpty()) {
                    return ResponseEntity.badRequest().body(errores);
                }
                return ResponseEntity.ok("CSV cargado correctamente con " + products.size() + " productos.");

            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error procesando CSV: " + e.getMessage());
            }
    }



}
