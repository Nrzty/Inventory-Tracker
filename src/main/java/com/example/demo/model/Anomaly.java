package com.example.demo.model;

public class Anomaly {

    private String productId;
    private String productName;
    private String message;

    public Anomaly() {}

    public Anomaly(String productId, String productName, String message) {
        this.productId = productId;
        this.productName = productName;
        this.message = message;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
