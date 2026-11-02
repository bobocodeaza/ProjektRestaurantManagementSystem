package com.example.restaurant.model;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private String id;
    private int number;
    private String occupiedStatus; // "Free/Occupied"
    private int capacity; // Extindere cerută
    private List<String> orders;

    public Table() { this.orders = new ArrayList<>(); }
    public Table(String id, int number, String occupiedStatus, int capacity) {
        this.id = id; this.number = number; this.occupiedStatus = occupiedStatus;
        this.capacity = capacity; this.orders = new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public String getOccupiedStatus() { return occupiedStatus; }
    public void setOccupiedStatus(String occupiedStatus) { this.occupiedStatus = occupiedStatus; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public List<String> getOrders() { return orders; }
    public void setOrders(List<String> orders) { this.orders = orders; }
}