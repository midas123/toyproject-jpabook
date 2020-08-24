package com.example.demo.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.example.demo.domain.Category;
import com.example.demo.exception.NotEnoughStockException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="dtype")
@Getter 
//@Setter //엔티티 필드 값은 비지니스 로직 메서드로 변경
public abstract class Item {
	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	@ManyToMany(mappedBy="items")
	private List<Category> categories = new ArrayList<>();
	
	//비지니스 로직
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	public void subtractStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if(restStock <0) {
			throw new NotEnoughStockException("남은 수량이 없습니다.");
		}
		this.stockQuantity = restStock;
	}
}
