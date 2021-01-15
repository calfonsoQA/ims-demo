package com.qa.ims.controller;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private CrudServices<Order> orderService;

	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}

	String getInput() {
		return Utils.getInstance().getInput();
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */

	public Order create() {
		Order o = new Order();
		List<Long> items = new ArrayList<>();
		LOGGER.info("Please enter the customer id");
		Long customer_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the item id or enter done");
		while (getInput().toLowerCase() != "done") {
			items.add(Long.valueOf(getInput()));
		}
		Long item_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the date");
		String date = getInput();
		// Long order_id = o.getId();
		Long order_id = 1L;
		// Order order = orderService.create(new Order(customer_id, date));

		Order order = orderService.create(new Order(order_id, customer_id, item_id, date));

		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("Please enter the customer id");
		Long customer_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the item id");
		Long item_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the order date");
		String date = String.valueOf(getInput());
		Order order = orderService.update(new Order(id, id, customer_id, item_id, date));
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
	}

}
