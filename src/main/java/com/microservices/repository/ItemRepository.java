package com.microservices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.model.Item;

public interface ItemRepository extends MongoRepository<Item,String> {
	 Item findOneById(String id);
}
