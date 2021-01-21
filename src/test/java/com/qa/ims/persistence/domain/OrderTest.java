package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private Order order;
	private Order otherOrder;
	private Order emptyOrder;
	private Order orderWithPrice;
	private Order orderBuilder;
	private Order orderWithID;
	private Order orderNoID;
	
	
	@Before
	public void setUp() {
		order = new Order(1L, 1L, 1L, 10, "January");
		otherOrder = new Order(1L, 1L, 1L, 10, "January");
		emptyOrder = new Order();
		
		orderWithPrice = new Order(4L, 1L, 1L, 10, "January").total_price(9.99);
		List<Long> items_id = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();
		List<Long> items_id_delete = new ArrayList<>();
		items_id.add(1L);
		quantities.add(1);
		items_id_delete.add(1L);
		orderWithID = new Order(1L, 1L, items_id, "January");
		orderNoID = new Order(1L, items_id, "January");
		orderBuilder = new Order().quantities(quantities).items_id_delete(items_id_delete).updateAddItems(true).updateDeleteItems(true); // For remaining builders
	}
	@Test
	public void settersTest() {
		assertNotNull(order.getId());
		assertNotNull(order.getCustomer_id());
		assertNotNull(order.getItem_id());
		assertNotNull(order.getQuantity());
		assertNotNull(order.getOrder_date());
		assertNotNull(orderBuilder);
		assertNotNull(orderWithPrice);
		assertNotNull(orderWithID);
		assertNotNull(orderNoID);
		
		
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
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}
	
	@Test
	public void createCustomerWithId() {
		assertEquals(1L, order.getId(), 0);
		assertEquals(1L, order.getCustomer_id(),0);
		assertEquals(1L, order.getItem_id(),0);
	}
	
	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}
	
	
	
	@Test
	public void customerIDNullButOtherNameNotNull() {
		order.setCustomer_id(null);
		assertFalse(order.equals(otherOrder));
	}
	
	@Test
	public void customerIDNotEqual() {
		otherOrder.setCustomer_id(2L);
		assertFalse(order.equals(otherOrder));
	}
	
	@Test
	public void nullId() {
		order.setOrder_id(null);
		assertFalse(order.equals(otherOrder));
	}
	
	@Test
	public void otherIdDifferent() {
		otherOrder.setOrder_id(2L);
		assertFalse(order.equals(otherOrder));
	}
	
	@Test
	public void nullItemID() {
		order.setItem_id(null);
		assertFalse(order.equals(otherOrder));
	}
	@Test
	public void emptyOrder() {
		assertNotNull(emptyOrder);
	}
	@Test
	public void toStringTest() {
		String toString = "Order [order_id= 4, customer_id= 1, item_id= 1, quantity= 10, order_date= January, total price= £9.99]";
		assertEquals(toString, orderWithPrice.toString());
	}
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(otherOrder));
		}
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		order.setCustomer_id(null);
		otherOrder.setCustomer_id(null);
		assertTrue(order.equals(otherOrder));
	}
	@Test
	public void nullIdOnBoth() {
		order.setOrder_id(null);
		otherOrder.setOrder_id(null);
		assertTrue(order.equals(otherOrder));
	}	

}