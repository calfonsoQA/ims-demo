package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private Order order;
	private Order otherOrder;
	
	@Before
	public void setUp() {
		order = new Order(1L, 1L, 1L, 10, "January");
		otherOrder = new Order(1L, 1L, 1L, 10, "January");
	}
	@Test
	public void settersTest() {
		assertNotNull(order.getId());
		assertNotNull(order.getCustomer_id());
		assertNotNull(order.getItem_id());
		assertNotNull(order.getQuantity());
		assertNotNull(order.getOrder_date());
		
		
		order.setOrder_id(null);
		assertNull(order.getId());
		order.setCustomer_id(null);
		assertNull(order.getCustomer_id());
		order.setItem_id(null);
		assertNull(order.getItem_id());
		order.setQuantity(null);
		assertNull(order.getQuantity());
		order.setOrder_date(null);
		assertNull(order.getOrder_date());
		order.setItems_id(null);
		assertNull(order.getItems_id());
		order.setQuantities(null);
		assertNull(order.getQuantities());
		order.setTotal_price(null);
		assertNull(order.getTotal_price());
		order.setUpdateAddItems(null);
		assertNull(order.getUpdateAddItems());
		order.setUpdateDeleteItems(null);
		assertNull(order.getUpdateDeleteItems());
		order.setItems_id_delete(null);
		assertNull(order.getItems_id_delete());
		
	}

}
