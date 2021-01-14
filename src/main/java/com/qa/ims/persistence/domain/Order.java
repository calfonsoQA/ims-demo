package com.qa.ims.persistence.domain;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	
	private Long id;
	private Long ordItem_id;
	private Long customer_id;
	private Long item_id;
	private String order_date;
	
	
	
	public Order(Long id, Long customer_id, String order_date) {
	
		this.id = id;
		this.customer_id = customer_id;
		this.order_date = order_date;
	}
//	public Order(Long customer_id, Long item_id, String order_date) {
//		this.customer_id = customer_id;
//		this.item_id = item_id;
//		this.order_date = order_date;
//	}
	public Order(Long ordItem_id, Long customer_id, Long item_id, String order_date) {
		this.ordItem_id = ordItem_id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.order_date = order_date;
	}
	public Order(Long id, Long ordItem_id, Long customer_id, Long item_id, String order_date) {
		this.id = id;
		this.ordItem_id = ordItem_id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.order_date = order_date;
	}
	@Override
	public String toString() {
		return "Order [order_id= " + id + ", customer_id= " + customer_id + ", item_id= " + item_id + ", order_date= "
				+ order_date + "]";
	}
	public Long getId() {
		return id;
	}
	public void setOrder_id(Long id) {
		this.id = id;
	}
	public Long getOrdItem_id() {
		return ordItem_id;
	}
	public void setOrdItem_id(Long ordItem_id) {
		this.ordItem_id = ordItem_id;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

}
