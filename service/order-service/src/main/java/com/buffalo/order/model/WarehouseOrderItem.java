
package com.buffalo.order.model;
import com.buffalo.util.ModelUtil;

public class WarehouseOrderItem extends ModelUtil {

	private String id;
	private String warehouse_order_id;
	private String product_id;
	private int quantity;
	private int number_of_boxes;
	private int remainder;

	private String product_name;
	private int product_scale;
	private int product_index;

	public WarehouseOrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWarehouse_order_id() {
		return warehouse_order_id;
	}

	public void setWarehouse_order_id(String warehouse_order_id) {
		this.warehouse_order_id = warehouse_order_id;
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

	public int getNumber_of_boxes() {
		return number_of_boxes;
	}

	public void setNumber_of_boxes(int number_of_boxes) {
		this.number_of_boxes = number_of_boxes;
	}

	public int getRemainder() {
		return remainder;
	}

	public void setRemainder(int remainder) {
		this.remainder = remainder;
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
