// Book.java
package com.gfg.Spring.boot.app.model;

import com.gfg.Spring.boot.app.service.Warehouse;

public class Book implements Storable {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public void sendToWarehouse(Warehouse warehouse) {
        warehouse.putItem(this);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" + title + '}';
    }
}