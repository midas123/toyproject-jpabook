package com.example.demo.manytomany;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    /*
    * N:M 단방향
    * */
    @ManyToMany
    @JoinTable(name="MEMBER_PRODUCT")
    List<Product> products = new ArrayList<>();
}
