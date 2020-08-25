package com.example.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Order;

import lombok.RequiredArgsConstructor;

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
}
