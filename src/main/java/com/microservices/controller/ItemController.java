package com.microservices.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservices.model.Item;
import com.microservices.result.Result;
import com.microservices.service.ItemServiceIF;

@RequestMapping("/items/v1")
@Controller
public class ItemController {
	final Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemServiceIF itemServiceIF;

	public ItemServiceIF getItemServiceIF() {
		return itemServiceIF;
	}

	public void setItemServiceIF(ItemServiceIF itemServiceIF) {
		this.itemServiceIF = itemServiceIF;
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getItems() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<Item> itemList = null;
		try {
			itemList = itemServiceIF.getItems();
			if (itemList.isEmpty()) {
				result.setObject(itemList);
			} else {
				result.setObject(itemList);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(itemList);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getItem(@PathVariable("itemId") String itemId) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object item = null;
		try {
			item = itemServiceIF.getItem(itemId);
			if (item == null) {
				result.setObject(item);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(item);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(item);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}
		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> saveCart(@RequestBody Item item) {

		HttpStatus statusCode = null;
		Result result = new Result();
		try {
			if (item == null) {
				result.setObject(item);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				logger.info("item.getId() else: "+item.getId());
				item.setId(new ObjectId().toString());
				itemServiceIF.saveItem(item);
				result.setObject(item);
				statusCode = HttpStatus.OK;
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(item);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}
		return new ResponseEntity<Result>(result, statusCode);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> updateItem(@RequestBody  Item item) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object item1 = null;
		try {
			if (item == null) {
				result.setObject(item);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				item1 = itemServiceIF.updateItem(item);
				if(item1 == null){
					result.setObject(item1);
					statusCode = HttpStatus.NO_CONTENT;
				}
				else{
				result.setObject(item1);
				statusCode = HttpStatus.OK;
				}
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(item);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteItem(@PathVariable("itemId") String itemId) {
		HttpStatus statusCode = null;
		Result result = new Result();
		Item item = null;
		try {
			item = itemServiceIF.getItem(itemId);
			itemServiceIF.delete(item);
			if (item == null) {
				result.setObject(item);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(item);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(item);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

}
