
package com.buffalo.gateway.order.model;
import com.buffalo.gateway.util.ModelUtil;

import java.util.Date;
import java.util.List;

public class DelivererOrder extends ModelUtil {

	private String id;
	private Date order_date;
	private String driver_id;
	private String store_id;
	private Date delivery_date;
	private String order_recorder_id;
	private String total_price;
	private String line_id;
	private int is_out;

	private String driver;
	private String  out_order_recorder;
	private String line_name;
	private String store_name;
	private String store_holder;
	private String address;
	private Date bound_time;

	private List<DelivererOrderItem> itemList;

	public DelivererOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public Date getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	public String getOrder_recorder_id() {
		return order_recorder_id;
	}

	public void setOrder_recorder_id(String order_recorder_id) {
		this.order_recorder_id = order_recorder_id;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}

	public String getLine_id() {
		return line_id;
	}

	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

	public int getIs_out() {
		return is_out;
	}

	public void setIs_out(int is_out) {
		this.is_out = is_out;
	}

	public List<DelivererOrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<DelivererOrderItem> itemList) {
		this.itemList = itemList;
	}


	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getOut_order_recorder() {
		return out_order_recorder;
	}

	public void setOut_order_recorder(String out_order_recorder) {
		this.out_order_recorder = out_order_recorder;
	}

	public String getLine_name() {
		return line_name;
	}

	public void setLine_name(String line_name) {
		this.line_name = line_name;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_holder() {
		return store_holder;
	}

	public void setStore_holder(String store_holder) {
		this.store_holder = store_holder;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBound_time() {
		return bound_time;
	}

	public void setBound_time(Date bound_time) {
		this.bound_time = bound_time;
	}
}
