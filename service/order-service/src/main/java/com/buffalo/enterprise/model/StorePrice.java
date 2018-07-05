
package com.buffalo.enterprise.model;
import com.buffalo.util.ModelUtil;

public class StorePrice extends ModelUtil {

	private String id;
	private String store_id;
	private String product_id;
	private String unit_price;

	private String product_name;
	private int product_scale;
	private int product_index;

	public StorePrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_scale() {
		return product_scale;
	}

	public void setProduct_scale(int product_scale) {
		this.product_scale = product_scale;
	}

	public int getProduct_index() {
		return product_index;
	}

	public void setProduct_index(int product_index) {
		this.product_index = product_index;
	}
}
