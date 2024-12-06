package sde.group.iii.inventorysystem.model;

import java.time.LocalDate;

public class Sales {
    private int id;
    private int itemId;
    private int customerId;
    private LocalDate saleDate;
    private double amount;

    // Constructors
    public Sales() {
    }

    public Sales(int id, int itemId, int customerId, LocalDate saleDate, double amount) {
        this.id = id;
        this.itemId = itemId;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.amount = amount;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
