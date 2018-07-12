
package com.buffalo.order.model;
import com.buffalo.util.ModelUtil;

import java.util.Date;
import java.util.List;

public class WarehouseOrder extends ModelUtil {

	private String id;
	private Date order_date;
	private String deliverer_id;
	private Date delivery_date;
	private String order_recorder_id;
	private String type;
	private String line_id;

	private String deliverer;
	private String  out_order_recorder;
	private String line_name;


	private List<WarehouseOrderItem> itemList;

	public WarehouseOrder() {
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

	public String getDeliverer_id() {
		return deliverer_id;
	}

	public void setDeliverer_id(String deliverer_id) {
		this.deliverer_id = deliverer_id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLine_id() {
		return line_id;
	}

	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer;
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

	public List<WarehouseOrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<WarehouseOrderItem> itemList) {
		this.itemList = itemList;
	}
}
