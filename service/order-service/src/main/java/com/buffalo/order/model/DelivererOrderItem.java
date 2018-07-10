
package com.buffalo.order.model;
import com.buffalo.util.ModelUtil;

public class DelivererOrderItem extends ModelUtil {

	private String id;
	private String deliverer_order_id;
	private String product_id;
	private int quantity;

	private String product_name;
	private int product_scale;
	private int product_index;

	public DelivererOrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeliverer_order_id() {
		return deliverer_order_id;
	}

	public void setDeliverer_order_id(String deliverer_order_id) {
		this.deliverer_order_id = deliverer_order_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
