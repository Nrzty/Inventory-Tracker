package com.example.demo.service;

import com.example.demo.model.Anomaly;
import com.example.demo.model.InventoryMovement;
import com.example.demo.model.ProductStock;
import com.example.demo.model.response.InventoryResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public InventoryResponse analyze(List<InventoryMovement> movements) {
        Map<String, ProductStock> stockMap = new HashMap<>();
        List<Anomaly> anomalies = new ArrayList<>();
        Set<String> anomalyIds = new HashSet<>();

        for (InventoryMovement movement : movements) {
            if (!stockMap.containsKey(movement.getProductId())) {
                ProductStock newStock = new ProductStock(
                    movement.getProductId(),
                    movement.getProductName(),
                    0
                );

                stockMap.put(movement.getProductId(), newStock);
            }

            ProductStock stock = stockMap.get(movement.getProductId());

            if (movement.getType().equals("in")) {
                stock.setQuantity(stock.getQuantity() + movement.getQuantity());
            } else {
                stock.setQuantity(stock.getQuantity() - movement.getQuantity());
            }

            if (stock.getQuantity() < 0) {
                if (!anomalyIds.contains(movement.getProductId())) {
                    anomalyIds.add(movement.getProductId());
                    Anomaly anomaly = new Anomaly(
                        movement.getProductId(),
                        movement.getProductName(),
                        "Stock went negative"
                    );
                    anomalies.add(anomaly);
                }
            }
        }
        List<ProductStock> stock = new ArrayList<>(stockMap.values());
        List<ProductStock> lowStock = new ArrayList<>();

        for (ProductStock productStock : stock) {
            if (productStock.getQuantity() < 10) {
                lowStock.add(productStock);
            }
        }

        return new InventoryResponse(stock, lowStock, anomalies);
    }
}
