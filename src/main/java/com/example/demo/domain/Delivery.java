package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
	@Id @GeneratedValue
	@Column(name="delivery_id")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy="delivery")
	private Order order;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING) 
	//enum 값을 int로 저장하는 ORDINAL 사용금지(추후 enum 값이 새로 추가될 경우 장애 가능성 있음)  
	private DeliveryStatus status;
}
