package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_id");
		//Long orditem_id = resultSet.getLong("orditems_id");
		Long customer_id = resultSet.getLong("customer_id");
		Long item_id = resultSet.getLong("item_id");
		String date = resultSet.getString("order_date");
		Double total_price = resultSet.getDouble("total_price");
		int quantity = resultSet.getInt("quantity");

		return new Order(id, customer_id, item_id, quantity, date).total_price(total_price);
		// Order(Long id, Long ordItem_id, Long customer_id, Long item_id, String
		// order_date)
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("select * from orders o JOIN ordersItems oi ON o.order_id=oi.order_id");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT * FROM orders o JOIN ordersItems oi ON o.order_id=oi.order_id ORDER BY o.order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Long readLatestOrderID() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return resultSet.getLong("order_id");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Double calcTotalPrice() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT SUM(price*quantity) AS total FROM items i JOIN ordersItems oi ON i.item_id=oi.item_id WHERE order_id="
								+ readLatestOrderID());) {
			resultSet.next();
			return resultSet.getDouble("total");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public Double updateTotalPrice(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT SUM(price*quantity) AS total FROM items i JOIN ordersItems oi ON i.item_id=oi.item_id WHERE order_id="
								+ order.getId());) {
			resultSet.next();
			return resultSet.getDouble("total");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders(customer_id, order_date) values('" + order.getCustomer_id()
					+ "','" + order.getOrder_date() + "')");
			List<Long> items_id = new ArrayList<Long>();
			items_id = order.getItems_id();
			List<Integer> quantity = new ArrayList<Integer>();
			quantity = order.getQuantities();
			int j = 0;
			for (Long i : items_id) {
				statement.executeUpdate("INSERT INTO ordersItems(order_id, item_id, quantity) values('"	+ readLatestOrderID() + "','" + i + "','" + quantity.get(j) + "')");
				j++;
			}
			statement.executeUpdate(
					"update orders set total_price ='" + calcTotalPrice() + "' where order_id =" + readLatestOrderID());
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readOrder(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT * FROM orders o JOIN ordersItems oi ON o.order_id=oi.order_id where o.order_id = "+ id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orders set customer_id ='" + order.getCustomer_id() + "', order_date ='"
					+ order.getOrder_date() + "' where order_id =" + order.getId());
//			statement.executeUpdate("update ordersItems set item_id ='" + order.getItem_id() + "' where order_id =" + order.getId());
			List<Long> items_id = new ArrayList<Long>();
			items_id = order.getItems_id();
			List<Integer> quantity = new ArrayList<Integer>();
			quantity = order.getQuantities();
			boolean updateAddItems = order.getUpdateAddItems();
			boolean updateDeleteItems = order.getUpdateDeleteItems();
			List<Long> itemsDelete = new ArrayList<Long>();
			itemsDelete = order.getItems_id_delete();
			if (updateDeleteItems) {
				//int j = 0;
				for (Long i : itemsDelete) {
					statement.executeUpdate("delete from ordersItems WHERE item_id = " + i + " AND order_id = " + order.getId());
				}
			}
			if (updateAddItems) {
				int j = 0;
				for (Long i : items_id) {
					statement.executeUpdate("INSERT INTO ordersItems(order_id, item_id, quantity) values('"	+ order.getId() + "','" + i + "','" + quantity.get(j) + "')");
					j++;
				}
			}
			statement.executeUpdate("update orders set total_price ='" + updateTotalPrice(order) + "' where order_id =" + order.getId());
			return readOrder(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from ordersItems where order_id = " + id);
			statement.executeUpdate("delete from orders where order_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
