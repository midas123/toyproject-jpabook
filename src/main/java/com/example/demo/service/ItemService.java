package com.example.demo.service;

import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public List<Item> findItems(){
		return itemRepository.findAll();
	}

	@Transactional
	public Item updateItem(Long itemId, Book param){
		Item item = itemRepository.findOne(itemId);
		item.updateItemStockAndPrice(param);
		//item.setPrice(param.getPrice());
		//item.setStockQuantity(param.getStockQuantity());
		return item;
	}

	@Transactional
	public void updateBook(Long itemId,
						   String name,
						   int price,
						   int stockQuantity,
						   String author,
						   String isbn
	){
		Book item = (Book) itemRepository.findOne(itemId);
		item.setName(name);
		item.setPrice(price);
		item.setStockQuantity(stockQuantity);
		item.setAuthor(author);
		item.setIsbn(isbn);
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}




}
