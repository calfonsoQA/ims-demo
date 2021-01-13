package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Item;

@RunWith(MockitoJUnitRunner.class)
public class ItemServicesTest {
	
	@Mock
	private Dao<Item> customerDao;
	
	@InjectMocks
	private ItemServices itemServices;
	
	@Test
	public void itemServicesCreate() {
		Item customer = new Item("tomato", 12.09);
		itemServices.create(customer);
		Mockito.verify(customerDao, Mockito.times(1)).create(customer);
	}
	
	@Test
	public void itemServicesRead() {
		itemServices.readAll();
	Mockito.verify(customerDao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void itemServicesUpdate() {
		Item item = new Item("tomato", 12.09);
		itemServices.update(item);
		Mockito.verify(customerDao, Mockito.times(1)).update(item);
	}
	
	@Test
	public void itemServicesDelete() {
		itemServices.delete(1L);;
		Mockito.verify(customerDao, Mockito.times(1)).delete(1L);
	}
}
