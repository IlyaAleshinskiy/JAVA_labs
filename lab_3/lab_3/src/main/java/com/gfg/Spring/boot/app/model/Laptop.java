// Laptop.java
package com.gfg.Spring.boot.app.model;

import com.gfg.Spring.boot.app.service.Warehouse;

public class Laptop implements Storable {
    private String brand;

    public Laptop(String brand) {
        this.brand = brand;
    }

    @Override
    public void sendToWarehouse(Warehouse warehouse) {
        warehouse.putItem(this);
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Laptop{" + brand + '}';
    }
}