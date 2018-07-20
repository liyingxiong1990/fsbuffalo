
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.mapper.ProductMapper;
import com.buffalo.gateway.enterprise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<Product> list(String keyword) throws Exception {
		Product product = new Product();
		product.setKeyword(keyword);
		return productMapper.list(product);
	}

	@Override
	public Product getById(String id) throws Exception {
		return productMapper.getById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Product add(Product product) throws Exception {
		productMapper.add(product);
		return product;
	}

	@Override
	public void update(Product product) throws Exception {
		productMapper.update(product);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Product product) throws Exception {
		productMapper.delete(product);
	}


}
