// ToolBox.java
package com.gfg.Spring.boot.app.model;

import com.gfg.Spring.boot.app.service.Warehouse;

public class ToolBox implements Storable {
    private int toolCount;

    public ToolBox(int toolCount) {
        this.toolCount = toolCount;
    }

    @Override
    public void sendToWarehouse(Warehouse warehouse) {
        warehouse.putItem(this);
    }

    public int getToolCount() {
        return toolCount;
    }

    @Override
    public String toString() {
        return "ToolBox{" + toolCount + " tools}";
    }
}
