package com.example.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Order;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class OrderRepository {
	private final EntityManager em;

	@Transactional
	public void save(Order order) {
		em.persist(order);
	}
	
	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

	public List<Order> findAll(OrderSearch orderSearch){
		return em.createQuery("select o from Order o join o.member m"+
						" where o.status = :status "+
						"and m.name like :name" ,Order.class)
				.setMaxResults(1000)
				.getResultList();
	}
}
