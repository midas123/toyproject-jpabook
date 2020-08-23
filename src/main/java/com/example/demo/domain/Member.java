package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter //가급적 사용하지 않아야함, setter를 남용하면 엔티티 변경 지점이 불명확해지면서 유지보수가 어려워짐
@Entity
public class Member {

	@Id @GeneratedValue
	@Column(name="member_id")
	private Long id;
	
	private String name;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member") //order 테이블의 member 필드를 연관 관계 주인으로 설정
	//컬렉션은 필드에서 바로 초기화 하고 추후에도 손대지 않는게 best practice
	private List<Order> orders = new ArrayList<>(); 
}
