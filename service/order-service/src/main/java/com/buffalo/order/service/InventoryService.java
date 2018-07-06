
package com.buffalo.order.service;

import com.buffalo.order.model.Inventory;

import java.util.List;

public interface InventoryService {

    public List<Inventory> list(String keyword) throws Exception;
    
    public Inventory add(Inventory inventory) throws Exception;

    public Inventory addBlank(Inventory inventory) throws Exception;

    public void update(Inventory inventory) throws Exception;

    public void delete(Inventory inventory) throws Exception;


}
