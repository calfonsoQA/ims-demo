package com.qa.ims.persistence.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private Long id;
	private Long ordItem_id;
	private Long customer_id;
	private Long item_id;
	private String order_date;
	private List<Long> items_id = new ArrayList<>();
	private int quantity; 
	private List<Integer> quantities = new ArrayList<>();
	private Double total_price;
	private boolean updateAddItems;
	private boolean updateDeleteItems;

	public Order() {

	}

	public Order(Long id) {
		super();
		this.id = id;
	}

	public Order(Long customer_id, String order_date) {

		this.customer_id = customer_id;
		this.order_date = order_date;
	}

//	public Order(Long id, Long customer_id, String order_date) {
//		super();
//		this.id = id;
//		this.customer_id = customer_id;
//		this.order_date = order_date;
//	}
	public Order(Long customer_id, List<Long> items_id, String order_date) {
		super();
		this.customer_id = customer_id;
		this.items_id = items_id;
		this.order_date = order_date;
	}

	public Order(Long id, Long customer_id, List<Long> items_id,String order_date) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.order_date = order_date;
		this.items_id = items_id;
	}
	
//	public Order(Long id, Long customer_id, Long item_id, String order_date) {
//		this.id = id;
//		this.customer_id = customer_id;
//		this.item_id = item_id;
//		this.order_date = order_date;
//	}
	

	public Order(Long id, Long ordItem_id, Long customer_id, Long item_id, String order_date) {
		this.id = id;
		this.ordItem_id = ordItem_id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.order_date = order_date;
	}

//	@Override
//	public String toString() {
//		return "Order [order_id= " + id + ", customer_id= " + customer_id + ", order_date= "
//				+ order_date + "]";
//	}
	@Override
	public String toString() {
		return "Order [order_id= " + id + ", customer_id= " + customer_id + ", item_id= " + item_id + ", quantity= " + quantity + ", order_date= "
				+ order_date + ", total price= £" + total_price +"]";
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

	public List<Long> getItems_id() {
		return items_id;
	}

	public void setItems_id(List<Long> items_id) {
		this.items_id = items_id;
	}

	public List<Integer> getQuantities() {
		return quantities;
	}

	public void setQuantities(List<Integer> quantities) {
		this.quantities = quantities;
	}
	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	
public boolean getUpdateAddItems() {
		return updateAddItems;
	}

	public void setUpdateAddItems(boolean updateAddItems) {
		this.updateAddItems = updateAddItems;
	}

	public boolean getUpdateDeleteItems() {
		return updateDeleteItems;
	}

	public void setUpdateDeleteItems(boolean updateDeleteItems) {
		this.updateDeleteItems = updateDeleteItems;
	}

	//	BUILDERS
	public Order quantity(int quantity) {
		this.quantity = quantity;
		return this;
	}
	public Order quantities(List<Integer> quantities) {
		this.quantities = quantities;
		return this;
	}
	public Order total_price(Double total_price) {
		this.total_price = total_price;
		return this;
	}
	public Order updateAddItems(boolean updateAddItems) {
		this.updateAddItems = updateAddItems;
		return this;
	}
	public Order updateDeleteItems(boolean updateDeleteItems) {
		this.updateDeleteItems = updateDeleteItems;
		return this;
	}
	

}
