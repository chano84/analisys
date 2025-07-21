package com.dataart.analysis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @CsvBindByName
    private UUID productId;

    @CsvBindByName
    @NotBlank
    private String siteName;

    @CsvBindByName
    @NotBlank
    private String category;

    @CsvBindByName
    @NotBlank
    private String productName;

    @CsvBindByName
    @DecimalMin("0.0")
    private BigDecimal price;

    @CsvBindByName
    private String currency;

    @CsvBindByName
    private boolean available;

    @CsvBindByName
    @CsvDate("yyyy-MM-dd'T'HH:mm:ssX")
    private OffsetDateTime createdAt;

}
