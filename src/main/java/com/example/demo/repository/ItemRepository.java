package com.example.demo.repository;

import com.example.demo.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	private final EntityManager em;
	
	public void save(Item item) {
		if(item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
			/*
			값이 없는 필드가 null로 업데이트될 위험이 있는 병합(merge)은
			사용하지 않는게 좋다. 대신 변경 감지를 사용한다.
			*/
		}
	}
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
}
