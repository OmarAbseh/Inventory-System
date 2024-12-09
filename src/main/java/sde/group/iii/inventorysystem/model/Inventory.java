package sde.group.iii.inventorysystem.model;

import java.sql.Timestamp;

public class Inventory {
    private int id;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    private int readyToShipQuantity;
    private Timestamp dateAdded;  // New field for date_added

    // Default constructor
    public Inventory() {
    }

    // Constructor for initializing an inventory item with all attributes
    public Inventory(int id, String name, double price, String description, int stockQuantity, int readyToShipQuantity, Timestamp dateAdded) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.readyToShipQuantity = readyToShipQuantity;
        this.dateAdded = dateAdded;
    }

    // Constructor for initializing an inventory item without the ID, useful for adding new items
    public Inventory(String name, double price, String description, int stockQuantity, int readyToShipQuantity, Timestamp dateAdded) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.readyToShipQuantity = readyToShipQuantity;
        this.dateAdded = dateAdded;
    }

    public Inventory(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;

    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getReadyToShipQuantity() {
        return readyToShipQuantity;
    }

    public void setReadyToShipQuantity(int readyToShipQuantity) {
        this.readyToShipQuantity = readyToShipQuantity;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
}
