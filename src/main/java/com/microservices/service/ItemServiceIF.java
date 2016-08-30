package com.microservices.service;

import java.util.List;

import com.microservices.exception.ItemException;
import com.microservices.model.Item;

public interface ItemServiceIF {
	public List<Item> getItems() throws ItemException;
	public Item getItem(String id) throws ItemException;
	public Item saveItem(Item item) throws ItemException;
	public Item updateItem(Item item) throws ItemException;
	public void delete(Item book) throws ItemException;
	public void deleteAll() throws ItemException; 
	
}
