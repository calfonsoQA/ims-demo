package com.qa.ims.persistence.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	
	public Order modelOrderItemFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("customer_id");
		String date = resultSet.getString("order_date");
		return new Order(id, customer_id, date);
	}
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_id");
		Long orditem_id = resultSet.getLong("orditems_id");
		Long customer_id = resultSet.getLong("customer_id");
		Long item_id = resultSet.getLong("item_id");
		String date = resultSet.getString("order_date");
		return new Order(id, orditem_id, customer_id, item_id, date);
		
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelOrderItemFromResultSet(resultSet));
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
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders o JOIN ordersItems oi ON o.order_id=oi.order_id ORDER BY o.order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order readLatestOrder() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelOrderItemFromResultSet(resultSet);
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
			statement.executeUpdate("INSERT INTO orders(customer_id, order_date) values('" + order.getCustomer_id()+ "','" + order.getOrder_date() + "')");
			//readLatestOrder();
			//statement.executeUpdate("INSERT INTO ordersItems(order_id, item_id) values('" + order.getId() + "','" + order.getItem_id() + "')");
			return readLatestOrder();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public Order createOrderItem(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			//readLatestOrder();
			statement.executeUpdate("INSERT INTO ordersItems(order_id, item_id) values('" + order.getId() + "','" + order.getItem_id() + "')");
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
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders o JOIN ordersItems oi ON o.order_id=oi.orditems_id where o.order_id = " + id);) {
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
			statement.executeUpdate("update ordersItems set item_id ='" + order.getItem_id() + "' where orditems_id =" + order.getId());
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
			statement.executeUpdate("delete from orders where id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
