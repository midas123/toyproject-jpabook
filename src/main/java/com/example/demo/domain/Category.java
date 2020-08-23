package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.example.demo.domain.item.Item;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {
	@Id @GeneratedValue
	@Column(name = "category_id")
	private Long id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name="category_name",
		joinColumns = @JoinColumn(name="category_id"),
		inverseJoinColumns = @JoinColumn(name="item_id"))
	//중간 테이블 설정. 관계형DB에서는 엔티티를 직접 N:N로 연관 관계 설정이 안됨. 
	//다른 엔티티와 추가적인 연관 관계 설정이 안되므로 실무에서는 거의 사용 안함 
	private List<Item> items = new ArrayList<>();
	
	//셀프 연관 관계 설정으로 카테고리의 계층 구조 구현
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private Category parent;
	
	@OneToMany(mappedBy="parent")
	private List<Category> childs = new ArrayList<>();
	
	//연관 관계 메서드
	public void addChildCategory(Category child) {
		this.childs.add(child);
		child.setParent(this);
	}
}
