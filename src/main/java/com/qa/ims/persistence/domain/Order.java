package com.qa.ims.persistence.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private Long id;
	private Long customer_id;
	private Long item_id;
	private String order_date;
	private List<Long> items_id = new ArrayList<>();
	private Integer quantity; 
	private List<Integer> quantities = new ArrayList<>();
	private Double total_price;
	private Boolean updateAddItems;
	private Boolean updateDeleteItems;
	private List<Long> items_id_delete = new ArrayList<>();

	public Order() {

	}

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

	public Order(Long id, Long customer_id, Long item_id, Integer quantity, String order_date) {
		this.id = id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.order_date = order_date;
	}

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
	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	
public Boolean getUpdateAddItems() {
		return updateAddItems;
	}

	public void setUpdateAddItems(Boolean updateAddItems) {
		this.updateAddItems = updateAddItems;
	}

	public Boolean getUpdateDeleteItems() {
		return updateDeleteItems;
	}

	public void setUpdateDeleteItems(Boolean updateDeleteItems) {
		this.updateDeleteItems = updateDeleteItems;
	}
	public List<Long> getItems_id_delete() {
		return items_id_delete;
	}

	public void setItems_id_delete(List<Long> items_id_delete) {
		this.items_id_delete = items_id_delete;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order otherOrder = (Order) obj;
		if (customer_id == null) {
			if (otherOrder.customer_id != null)
				return false;
		} else if (!customer_id.equals(otherOrder.customer_id))
			return false;
		if (id == null) {
			if (otherOrder.id != null)
				return false;
		} else if (!id.equals(otherOrder.id))
			return false;
		if (item_id == null) {
			if (otherOrder.item_id != null)
				return false;
		} else if (!item_id.equals(otherOrder.item_id))
			return false;
		if (quantity == null) {
			if (otherOrder.quantity != null)
				return false;
		} else if (!quantity.equals(otherOrder.quantity))
			return false;
		return true;
	}

//		BUILDERS
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
	public Order items_id_delete(List<Long> items_id_delete) {
		this.items_id_delete = items_id_delete;
		return this;
	}

	
	

}
