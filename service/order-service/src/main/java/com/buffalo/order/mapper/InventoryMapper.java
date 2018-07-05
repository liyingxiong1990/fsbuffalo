
package com.buffalo.order.mapper;

import com.buffalo.order.model.Inventory;
import com.buffalo.order.model.InventoryItem;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InventoryMapper {

    public List<Inventory> list(Inventory inventory) throws Exception;
    
    public void add(Inventory inventory) throws Exception;

    public void update(Inventory inventory) throws Exception;
    
    public void delete(Inventory inventory) throws Exception;

    public void addInventoryItem(InventoryItem InventoryItem) throws Exception;

    public void updateInventoryItem(InventoryItem InventoryItem) throws Exception;

}
