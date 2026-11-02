package com.example.restaurant.model;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String phoneNumber; // Extindere cerută
    private List<String> orders;

    public Customer() { this.orders = new ArrayList<>(); }
    public Customer(String id, String name, String phoneNumber) {
        this.id = id; this.name = name; this.phoneNumber = phoneNumber;
        this.orders = new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public List<String> getOrders() { return orders; }
    public void setOrders(List<String> orders) { this.orders = orders; }
}