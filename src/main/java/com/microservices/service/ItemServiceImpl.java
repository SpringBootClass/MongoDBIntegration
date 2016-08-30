package com.microservices.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.exception.ItemException;
import com.microservices.model.Item;
import com.microservices.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemServiceIF{

	final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getItems() throws ItemException {
		return itemRepository.findAll();
	}

	public Item getItem(String id)  throws ItemException {
		return itemRepository.findOneById(id);
	}

	public Item saveItem(Item item) throws ItemException {
		return itemRepository.save(item);
	}
	
	public Item updateItem(Item item) throws ItemException {
		return itemRepository.save(item);
	}

	public void delete(Item book) throws ItemException {
		itemRepository.delete(book);
	}

	/**
	 * Removes all Book entities from database.
	 */
	public void deleteAll() {
		itemRepository.deleteAll();
	}
}
