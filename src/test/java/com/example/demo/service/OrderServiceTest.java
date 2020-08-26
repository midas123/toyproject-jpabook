package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Address;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.exception.NotEnoughStockException;
import com.example.demo.repository.OrderRepository;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	EntityManager em;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	public void 상품주문() throws Exception {
		

		//given
		Member member = createMember();
		em.persist(member);

		Book book = createBook("책", 1000, 10);
		em.persist(book);
		int orderCount = 2;
		
		//when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		Order order = orderRepository.findOne(orderId);
		
		assertEquals("상품 주문 상태: ", OrderStatus.ORDER, order.getStatus());
		assertEquals("주문한 상품 종류는 몇 가지 인가", 1, order.getOrderItems().size());
		assertEquals("주문 가격", 1000*orderCount, order.getTotalPrice());
		assertEquals("재고 차감", 8, book.getStockQuantity());
	}
	
	@Test
	public void 주문취소() throws Exception {
		//given
		Member member = createMember();
		Item item = createBook("책", 1000, 10);
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

		//when
		orderService.cancelOrder(orderId);
		
		//then
		Order order = orderRepository.findOne(orderId);
		
		assertEquals("주문 취소 CANCEL", OrderStatus.CANCEL, order.getStatus());
		assertEquals("주문 취소 후 재고 수량", 10, item.getStockQuantity());
	}
	
	@Test(expected = NotEnoughStockException.class)
	public void 상품주문_재고수량초과() throws Exception {
		//given
		Member member = createMember();

		Item item = createBook("책", 1000, 10);
		
		int orderCount = 12;

		orderService.order(member.getId(), item.getId(), orderCount);
		//when
		
		//then
		fail("재고 수량 부족");
	}
	
	private Member createMember() {
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "경기", "123-123"));
		em.persist(member);

		return member;
	}
	
	private Book createBook(String name, int price, int counts) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(counts);
		em.persist(book);
		return book;
	}
}
