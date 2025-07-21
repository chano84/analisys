package com.dataart.analysis;

import com.dataart.analysis.controller.AnalysisController;
import com.dataart.analysis.dto.PriceAvgDTO;
import com.dataart.analysis.dto.ProductDTO;
import com.dataart.analysis.dto.SummaryDTO;
import com.dataart.analysis.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    private ProductService productService;
    private AnalysisController controller;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        controller = new AnalysisController(productService);
    }

    @Test
    void testGetAllMyEntities() {
        List<ProductDTO> mockProducts = List.of(new ProductDTO(), new ProductDTO());
        when(productService.getAll()).thenReturn(mockProducts);

        ResponseEntity<List<ProductDTO>> response = controller.getAllMyEntities();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(productService).getAll();
    }

    @Test
    void testGetSummary() {
        SummaryDTO mockSummary = new SummaryDTO();
        when(productService.getSummary()).thenReturn(mockSummary);

        ResponseEntity<SummaryDTO> response = controller.getSummary();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(productService).getSummary();
    }

    @Test
    void testGetTopProducts() {
        List<ProductDTO> topProducts = List.of(new ProductDTO());
        when(productService.getTopProducts()).thenReturn(topProducts);

        ResponseEntity<List<ProductDTO>> response = controller.getTopProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(productService).getTopProducts();
    }

    @Test
    void testGetTrend() {
        String productName = "Macbook";
        List<PriceAvgDTO> trendList = List.of(new PriceAvgDTO());
        when(productService.getTrend(productName)).thenReturn(trendList);

        ResponseEntity<List<PriceAvgDTO>> response = controller.getTrend(productName);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(productService).getTrend(productName);
    }
}
