package com.qa.ims.persistence.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item>  {
	
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("item_id");
		String itemName = resultSet.getString("item_name");
		double price = resultSet.getDouble("price");
		return new Item(id, itemName, price);
	}
	
	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from items");) {
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	private Item readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public Item readItem(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items where item_id = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Item create(Item t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO items(item_name, price) values('" + t.getItemName()
					+ "','" + t.getPrice() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Item update(Item t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update items set item_name ='" + t.getItemName() + "', price ='"
					+ t.getPrice() + "' where item_id =" + t.getId());
			return readItem(t.getId());
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
			statement.executeUpdate("delete from items where item_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		
	}

	

}
