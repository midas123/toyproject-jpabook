package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL) //order_id를 참조키로 사용하는 OrderItem이 연관 관계의 주인
	private List<OrderItem> orderItems = new ArrayList<>();
	
	//xToOne은 기본 값이 EAGER이므로 LAZY로 설정해야 함, EAGER는 연관 관계 테이블도 조회하므로 성능이 저하됨
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	@JoinColumn(name="delivery_id")
	// 1:1 연관 관계에서는 fk를 어디에 두어도 관계 없으나, 조회가 많은 쪽에 두는 것이 좋다.
	private Delivery delivery;
	
	private LocalDateTime orderDate; //java8에서는 LocalDateTime 사용시 날짜 자동 맵핑
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status; //ORDER, CANCEL
	
	//연관 관계 메서드, 양방향 관계 일때 양쪽 엔티티를 세팅해준다.
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
}
