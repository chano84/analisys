package com.dataart.analysis.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class PriceAvgDTO {

    String siteName;
    OffsetDateTime date;
    Double price;

    public PriceAvgDTO(){}
    public PriceAvgDTO(String siteName, OffsetDateTime date, Double price) {
        this.siteName = siteName;
        this.date = date;
        this.price = price;
    }

}
