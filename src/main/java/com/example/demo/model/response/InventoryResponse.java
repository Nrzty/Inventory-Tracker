package com.example.demo.model.response;

import com.example.demo.model.Anomaly;
import com.example.demo.model.ProductStock;
import java.util.List;

public class InventoryResponse {

    private List<ProductStock> stock;
    private List<ProductStock> lowStock;
    private List<Anomaly> anomalies;

    public InventoryResponse() {}

    public InventoryResponse(
        List<ProductStock> stock,
        List<ProductStock> lowStock,
        List<Anomaly> anomalies
    ) {
        this.stock = stock;
        this.lowStock = lowStock;
        this.anomalies = anomalies;
    }

    public List<ProductStock> getStock() {
        return stock;
    }

    public void setStock(List<ProductStock> stock) {
        this.stock = stock;
    }

    public List<ProductStock> getLowStock() {
        return lowStock;
    }

    public void setLowStock(List<ProductStock> lowStock) {
        this.lowStock = lowStock;
    }

    public List<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }
}
