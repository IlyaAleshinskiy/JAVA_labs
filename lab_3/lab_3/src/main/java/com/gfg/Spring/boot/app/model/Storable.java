package com.gfg.Spring.boot.app.model;

import com.gfg.Spring.boot.app.service.Warehouse;

public interface Storable {
    void sendToWarehouse(Warehouse warehouse);
}