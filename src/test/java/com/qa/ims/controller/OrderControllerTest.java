package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderServices;
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	/**
	 *  The thing I want to fake functionality for
	 */
	@Mock
	private OrderServices orderServices;
	
	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer controller
	 */
	@Spy
	@InjectMocks
	private OrderController orderController;

	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderServices);
		List<Order> orders = new ArrayList<>();
		List<Long> items_id = new ArrayList<>();
		List<Long> items_id2 = new ArrayList<>();
		List<Long> items_id3 = new ArrayList<>();
		items_id.add(1L);
		items_id2.add(2L);
		items_id3.add(3L);
		
		orders.add(new Order(1L,items_id, "January"));
		orders.add(new Order(2L,items_id2, "February"));
		orders.add(new Order(3L,items_id3, "March"));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

//	@Test
//	public void createTest() {
//		String customer_id = "1";
//		String item_id = "1";
//		String date = "January";
//		String quantity = "10";
//		List<Long> items_id = new ArrayList<>();
//		List<Integer> quantities = new ArrayList<>();
//		items_id.add(1L);
//		quantities.add(10);
//		Mockito.doReturn(customer_id, item_id, quantity, date).when(orderController).getInput();
//		Order order = new Order(1L, items_id,"January").quantities(quantities);
//		Order savedOrder = new Order(1L, 1L, items_id, "January").quantities(quantities);
//		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
//		assertEquals(savedOrder, orderController.create());
//	}

	/**
	 * 
	 */
//	@Test
//	public void updateTest() {
//		String id = "1";
//		String customer_id = "2";
//		String item_id = "2";
//		String quantity = "20";
//		String date = "February";
//		String updateAddItems = "true";
//		String updateDeleteItems = "false";
//		List<Long> items_id = new ArrayList<>();
//		List<Integer> quantities = new ArrayList<>();
//		items_id.add(1L);
//		quantities.add(20);
//		Mockito.doReturn(id, customer_id, item_id, quantity, date, updateAddItems, updateDeleteItems).when(orderController).getInput();
//		Order order = new Order(1L, 1L, items_id,date).quantities(quantities).updateAddItems(true).updateDeleteItems(false);
//		Mockito.when(orderServices.update(order)).thenReturn(order);
//		assertEquals(order, orderController.update());
//	}
	

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}
}
