package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class OrderDaoTest {
	private static final String DB_USER = "root";
	private static final String DB_PASS = "root";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ims_test";

	@Before
	public void setup() {
		DBUtils.getInstance(DB_USER, DB_PASS, DB_URL, true);

	}

	
	@Test
	public void createTest() {
		OrderDAO orderDAO = new OrderDAO();
		CustomerDaoMysql customerDAO = new CustomerDaoMysql();
		ItemDAO itemDAO = new ItemDAO();
		customerDAO.create(new Customer("John", "Doe"));
		itemDAO.create(new Item("Tomato", 12.99));
		Long customer_id = 1L;
		Long item_id = 1L;
		List<Long> items_id = new ArrayList<>();
		items_id.add(item_id);		
		List<Integer> quantities = new ArrayList<>();
		quantities.add(10);
		Order order = new Order(customer_id, items_id, "January").quantities(quantities);
		Order savedOrder = new Order(1L, customer_id, items_id, "January").quantities(quantities);
		assertEquals(savedOrder, orderDAO.create(order));
	}
	
	@Test
	public void readAllTest() {
		OrderDAO orderDAO = new OrderDAO();
		CustomerDaoMysql customerDAO = new CustomerDaoMysql();
		ItemDAO itemDAO = new ItemDAO();
		customerDAO.create(new Customer("John", "Doe"));
		itemDAO.create(new Item("Tomato", 12.99));
		List<Order> orders = new ArrayList<>();
		Long item_id = 1L;
		List<Long> items_id = new ArrayList<>();
		items_id.add(item_id);
		Long item_id2 = 1L;
		List<Long> items_id2 = new ArrayList<>();
		items_id2.add(item_id2);
		List<Integer> quantities = new ArrayList<>();
		quantities.add(10);
		List<Integer> quantities2 = new ArrayList<>();
		quantities2.add(10);
		orders.add(new Order(1L, 1L, items_id, "January").quantities(quantities));
		orders.add(new Order(1L, 1L, items_id2, "February").quantities(quantities2));
		orderDAO.create(new Order(1L, items_id, "January").quantities(quantities));
		orderDAO.create(new Order(1L, items_id2, "February").quantities(quantities2));
		assertEquals(orders, orderDAO.readAll());
	}

	@Test
	public void updateTest() {
		OrderDAO orderDAO = new OrderDAO();
		Long item_id = 1L;
		List<Long> items_id = new ArrayList<>();
		items_id.add(item_id);
		Long item_id2 = 1L;
		List<Long> items_id2 = new ArrayList<>();
		items_id2.add(item_id2);	
		Order order = new Order(1L, items_id, "January");
		Order newOrder= new Order(2L, items_id2, "February");
		orderDAO.create(order);
		assertEquals(newOrder, orderDAO.update(newOrder));
		assertNotEquals(order, orderDAO.update(newOrder));
	}
	@Test
	public void deleteTest() {
		OrderDAO orderDAO = new OrderDAO();

		orderDAO.delete(1L);
	}
}
