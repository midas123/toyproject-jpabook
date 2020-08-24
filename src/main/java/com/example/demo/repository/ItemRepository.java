package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	private final EntityManager em;
	
	public void save(Item item) {
		if(item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}
	}
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	public List<Item> findAll(){
		return em.createQuery("select i form Item i", Item.class).getResultList();
	}
}
