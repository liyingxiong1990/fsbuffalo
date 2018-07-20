
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> list(String keyword) throws Exception;

    public Product getById(String product_id) throws Exception;

    public Product add(Product product) throws Exception;

    public void update(Product product) throws Exception;

    public void delete(Product product) throws Exception;

}
