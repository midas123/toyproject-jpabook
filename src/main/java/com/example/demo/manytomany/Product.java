package com.example.demo.manytomany;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    @Column(name="product_id")
    private Long id;

    private String name;

    /*
    * N:M 양방향
    * */
    //@ManyToMany(mappedBy = "products")
    //private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    List<Product> products = new ArrayList<>();
}
