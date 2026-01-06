package com.t2406e.product.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private double totalPrice;
    private String status;
    private Timestamp createdAt;
    // Nên dùng thay cho Date
    // Nếu Spring Boot thì dùng LocalDateTime
    // Dùng Timestamp vì nó map trực tiếp với kiểu TIMESTAMP trong database,
    // đảm bảo lưu chính xác thời điểm tạo đơn hàng (ngày + giờ + phút + giây),
    // phù hợp nghiệp vụ thương mại điện tử.

    public Order() {}

    public Order(int id, int userId, double totalPrice, String status, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

