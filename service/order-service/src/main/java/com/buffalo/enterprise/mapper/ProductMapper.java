
package com.buffalo.enterprise.mapper;

import com.buffalo.enterprise.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductMapper {

    public List<Product> list(Product product) throws Exception;

    public Product getById(String id) throws Exception;

    public void add(Product product) throws Exception;

    public void update(Product product) throws Exception;
    
    public void delete(Product product) throws Exception;


}
