
package com.buffalo.enterprise.mapper;

import com.buffalo.enterprise.model.Store;
import com.buffalo.enterprise.model.StorePrice;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StoreMapper {

    public List<Store> list(Store store) throws Exception;
    
    public void add(Store store) throws Exception;

    public void update(Store store) throws Exception;
    
    public void delete(Store store) throws Exception;

    public void addStorePrice(StorePrice storePrice) throws Exception;

    public void updateStorePrice(StorePrice storePrice) throws Exception;

    public void deleteStorePrice(String store_id) throws Exception;
}
