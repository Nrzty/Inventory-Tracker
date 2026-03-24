package com.example.demo.controller;

import com.example.demo.model.InventoryMovement;
import com.example.demo.model.response.InventoryResponse;
import com.example.demo.service.InventoryService;
import com.example.demo.utils.CsvParser;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class InventoryController {

    private final InventoryService inventoryService;
    private final CsvParser csvParser;

    public InventoryController(
        InventoryService inventoryService,
        CsvParser csvParser
    ) {
        this.inventoryService = inventoryService;
        this.csvParser = csvParser;
    }

    @PostMapping("/analyze-inventory")
    public ResponseEntity<InventoryResponse> analyzeInventory(
        @RequestParam("file") MultipartFile file
    ) throws CsvValidationException, IOException {
        List<InventoryMovement> inventoryMovements = csvParser.parse(file);
        return ResponseEntity.ok(inventoryService.analyze(inventoryMovements));
    }
}
