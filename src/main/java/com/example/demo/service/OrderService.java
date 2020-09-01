package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.domain.item.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepoository;
	private final ItemRepository itemRepoository;
	
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		//엔티티 조회
		Member member = memberRepoository.findOne(memberId);
		Item item = itemRepoository.findOne(itemId);

		//배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		
		//주문상품
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		//주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//주문 저장
		orderRepository.save(order);
		//Order에 cascade = CascadeType.ALL에 의해서 delivery, orderItem를 DB에 저장
		// delivery, orderItem가 Order 외에 다른 엔티티와 연관 관계가 있으면 CascadeType.ALL는 사용하면 안됨
		return order.getId();
		
	}
	
	@Transactional
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		order.cancel();
	}
	
	public List<Order> findOrders(OrderSearch orderSearch){
		return orderRepository.findAllByCriteria(orderSearch);
	}
}

