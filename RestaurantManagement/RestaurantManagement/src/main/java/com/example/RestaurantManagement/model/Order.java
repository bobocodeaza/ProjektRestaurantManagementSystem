package com.example.restaurant.model;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private String customerId;
    private String tableId;
    private String status; // "Open/Closed"
    private List<OrderLine> orderLines;
    private List<String> assignments;

    public Order() {
        this.orderLines = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }
    public Order(String id, String customerId, String tableId, String status) {
        this.id = id; this.customerId = customerId; this.tableId = tableId; this.status = status;
        this.orderLines = new ArrayList<>(); this.assignments = new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getTableId() { return tableId; }
    public void setTableId(String tableId) { this.tableId = tableId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderLine> getOrderLines() { return orderLines; }
    public void setOrderLines(List<OrderLine> orderLines) { this.orderLines = orderLines; }
    public List<String> getAssignments() { return assignments; }
    public void setAssignments(List<String> assignments) { this.assignments = assignments; }
}