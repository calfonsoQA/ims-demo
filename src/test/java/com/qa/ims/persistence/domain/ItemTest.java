package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	
	private Item item;
	private Item other;
	
	@Before
	public void setUp() {
		item = new Item(1L, "Tomato", 12.09);
		other = new Item(1L, "Tomato", 12.09);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(item.getId());
		assertNotNull(item.getItemName());
		assertNotNull(item.getPrice());
		
		item.setId(null);
		assertNull(item.getId());
		item.setItemName(null);
		assertNull(item.getItemName());
		item.setPrice(null);
		assertNull(item.getPrice());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}
	
	@Test
	public void createItemWithId() {
		assertEquals(1L, item.getId(), 0);
		assertEquals("Tomato", item.getItemName());
		assertEquals(Double.valueOf(12.09), item.getPrice());
	}
	
	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}
	
//	@Test
//	public void checkEqualityBetweenDifferentObjects() {
//		assertTrue(item.equals(other));
//	}
	
	@Test
	public void itemNameNullButOtherNameNotNull() {
		item.setItemName(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void itemNamesNotEqual() {
		other.setItemName("kiwi");
		assertFalse(item.equals(other));
	}	
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(item.equals(other));
	}
	
	
	@Test
	public void otherPriceDifferent() {
		other.setPrice(1.99);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		Item item = new Item("Tomato", 12.09);
		assertNull(item.getId());
		assertNotNull(item.getItemName());
		assertNotNull(item.getPrice());
	}
	
	
	@Test
	public void toStringTest() {
		String toString = "id: 1 item name: Tomato price: £12.09";
		
		assertEquals(toString, item.toString());
	}
	
	@Test
	public void nullId() {
		item.setId(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		item.setId(null);
		other.setId(null);
		//assertTrue(item.equals(other));
		assertEquals(item, other);
	}
	@Test
	public void nullPrice() {
		item.setPrice(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullPriceOnBoth() {
		item.setPrice(null);
		other.setPrice(null);
		assertTrue(item.equals(other));
	}
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		item.setItemName(null);
		other.setItemName(null);
		assertTrue(item.equals(other));
		//assertEquals(item, other);
	}
}
