package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

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
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.qa.ims.IMS;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;
//import com.qa.ims.persistence.dao.CustomerDaoMysql;

public class CustomerDaoTest {

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
		CustomerDaoMysql customerDao = new CustomerDaoMysql();
		String fn = "john";
		String sn = "doe";
		Customer customer = new Customer(fn, sn);
		Customer savedCustomer = new Customer(1L,fn,sn);
		customer = customerDao.create(customer);
		assertEquals(savedCustomer, customer);
	}
	
	@Test
	public void readAllTest() {
		CustomerDaoMysql customerDAO = new CustomerDaoMysql();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L,"Chris", "P"));
		customers.add(new Customer(2L, "Rhys", "T"));
		customers.add(new Customer(3L, "Nic", "J"));
		customerDAO.create(new Customer("Chris", "P"));
		customerDAO.create(new Customer("Rhys", "T"));
		customerDAO.create(new Customer("Nic", "J"));
		assertEquals(customers, customerDAO.readAll());
	}
	@Test
	public void updateTest() {
		CustomerDaoMysql customerDAO = new CustomerDaoMysql();
		Long id = 1L;
		String firstName = "Rhys";
		String surname = "Thompson";
		Customer customer = new Customer(id, firstName, surname);
		
		assertEquals(customer, customerDAO.update(customer));
	}
	@Test
	public void deleteTest() {
		CustomerDaoMysql customerDAO = new CustomerDaoMysql();
		customerDAO.delete(1L);
	}

}
