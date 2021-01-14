package com.qa.ims.controller;

//import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
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
	 * Reads all customers to the logger
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
	@Override
	public Order create() {
		LOGGER.info("Please enter the customer id");
		Long customer_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the item id");
		Long item_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the date");
		String date = getInput();
		Order order = orderService.create(new Order(customer_id, item_id, date));
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
	 * Deletes an existing customer by the id of the customer
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
	}

}
