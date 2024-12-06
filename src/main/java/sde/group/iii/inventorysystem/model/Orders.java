package sde.group.iii.inventorysystem.model;

import java.time.LocalDate;

public class Orders {
    private int id;
    private int itemId;
    private int customerId;
    private String trackingNumber;
    private String shippingAddress;
    private LocalDate orderDate;
    private LocalDate shippingDate;

    // Constructors
    public Orders() {
    }

    public Orders(int id, int itemId, int customerId, String trackingNumber, String shippingAddress, LocalDate orderDate, LocalDate shippingDate) {
        this.id = id;
        this.itemId = itemId;
        this.customerId = customerId;
        this.trackingNumber = trackingNumber;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
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

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }
}
