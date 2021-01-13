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

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	
	/**
	 *  The thing I want to fake functionality for
	 */
	@Mock
	private ItemServices itemServices;
	
	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer controller
	 */
	@Spy
	@InjectMocks
	private ItemController itemController;

	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemServices);
		List<Item> items = new ArrayList<>();
		items.add(new Item("Tomato", 12));
		items.add(new Item("Potato", 12.00));
		items.add(new Item("Apple", 1.99));
		Mockito.when(itemServices.readAll()).thenReturn(items);
		assertEquals(items, itemController.readAll());
	}

//	@Test
//	public void createTest() {
//		String itemName = "Tomato";
//		double price = 12.09;
//		Mockito.doReturn(itemName, price).when(itemController).getInput();
//		Item item = new Item(itemName, price);
//		Item savedItem = new Item(1L, "Tomato", 12.09);
//		Mockito.when(itemServices.create(item)).thenReturn(savedItem);
//		assertEquals(savedItem, itemController.create());
//	}
//
//	/**
//	 * 
//	 */
//	@Test
//	public void updateTest() {
//		String id = "1";
//		String itemName = "Potato";
//		Double price = 12.22;
//		Mockito.doReturn(id, itemName, price).when(itemController).getInput();
//		Item item = new Item(1L, itemName, price);
//		Mockito.when(itemServices.update(item)).thenReturn(item);
//		assertEquals(item, itemController.update());
//	}
	

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(itemController).getInput();
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}
	
}
