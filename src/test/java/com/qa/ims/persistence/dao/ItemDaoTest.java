package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;


public class ItemDaoTest {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final String DB_USER = "root";
	private static final String DB_PASS = "root";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ims_test";

	
	@Before
	public void setup(){
		DBUtils.getInstance(DB_USER, DB_PASS, DB_URL, true);
	}
	
	@Test
	public void createTest() {
		ItemDAO itemDAO = new ItemDAO();
		String itemName = "Tomato";
		Item item = new Item(itemName, 12.09);
		Item savedItem = new Item(1L, "Tomato", 12.09);
		assertEquals(savedItem, itemDAO.create(item));
	}
	
	@Test
	public void readAllTest() {
		ItemDAO itemDAO = new ItemDAO();
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Tomato", 1.20));
		items.add(new Item(2L, "Potato", 12.00));
		items.add(new Item(3L, "Apple", 1.99));
		itemDAO.create(new Item("Tomato", 1.20));
		itemDAO.create(new Item("Potato", 12.00));
		itemDAO.create(new Item("Apple", 1.99));
		assertEquals(items, itemDAO.readAll());
	}
	@Test
	public void updateTest() {
		ItemDAO itemDAO = new ItemDAO();

		Item item = new Item("Tomato", 12.99);
		Item newItem = new Item(1L, "Potato", 1.99);
		itemDAO.create(item);
		assertEquals(newItem, itemDAO.update(newItem));
		assertNotEquals(item, itemDAO.update(newItem));
	}
	@Test
	public void deleteTest() {
		ItemDAO itemDAO = new ItemDAO();

		itemDAO.delete(1L);
	}

}

