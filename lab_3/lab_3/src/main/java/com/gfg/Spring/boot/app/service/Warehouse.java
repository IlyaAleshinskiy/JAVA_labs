package com.gfg.Spring.boot.app.service;

import com.gfg.Spring.boot.app.model.Storable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class Warehouse {

    private final List<Storable> items = new ArrayList<>();

    public void putItem(Storable item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.add(item);
        System.out.println("Item added to warehouse: " + item.getClass().getSimpleName());
    }

    public List<Storable> getItems() {
        return new ArrayList<>(items); // defensive copy
    }

    public int getItemCount() {
        return items.size();
    }

    // Метод для демонстрации исключений
    public Storable takeItem(int index, String requester) {
        if (requester == null || requester.trim().isEmpty()) {
            throw new SecurityException("Requester name is required");
        }
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("No item at index " + index);
        }
        Storable item = items.remove(index);
        System.out.println(requester + " took item: " + item.getClass().getSimpleName());
        return item;
    }
}