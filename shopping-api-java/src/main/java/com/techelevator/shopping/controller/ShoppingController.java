package com.techelevator.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.shopping.dao.ItemDao;
import com.techelevator.shopping.exception.ItemNotFoundException;
import com.techelevator.shopping.model.Item;


@RestController
@CrossOrigin
@RequestMapping("/api/groceries")
public class ShoppingController {
	
	private ItemDao itemDAO;
	
	@Autowired
	public ShoppingController(ItemDao ItemDAO) {
		this.itemDAO = ItemDAO;
	}
	
	@GetMapping
	public List<Item> list() {
		return itemDAO.list();
	}
	
	@GetMapping("/{id}")
	public Item read(@PathVariable int id) {
		Item item = itemDAO.read(id);
		if (item != null) {
			return item;
			
		} else {
			throw new ItemNotFoundException(id, "Item does Not Exist.");
		}
	}
	
	@PutMapping
	public Item update(@PathVariable int id, @RequestBody Item item) {
		Item requestedItem = itemDAO.read(id);
		if (item != null) {
			requestedItem.setId(id);
			return itemDAO.update(item);
		} else {
			throw new ItemNotFoundException(id, "Item does Not Exist.");
		}
	}
	
	

	
}
