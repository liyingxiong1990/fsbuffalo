
package com.buffalo.order.model;
import com.buffalo.util.ModelUtil;

import java.util.Date;
import java.util.List;

public class CheckinOrder extends ModelUtil {

	private String id;
	private Date checkin_date;
	private String in_order_recorder_id;
	private String carrier_id;
	private List<CheckinOrderItem> itemList;

	public CheckinOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCheckin_date() {
		return checkin_date;
	}

	public void setCheckin_date(Date checkin_date) {
		this.checkin_date = checkin_date;
	}

	public String getIn_order_recorder_id() {
		return in_order_recorder_id;
	}

	public void setIn_order_recorder_id(String in_order_recorder_id) {
		this.in_order_recorder_id = in_order_recorder_id;
	}

	public String getCarrier_id() {
		return carrier_id;
	}

	public void setCarrier_id(String carrier_id) {
		this.carrier_id = carrier_id;
	}

	public List<CheckinOrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CheckinOrderItem> itemList) {
		this.itemList = itemList;
	}
}
