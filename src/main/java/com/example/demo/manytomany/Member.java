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
    * N:M은 @JoinTable에 의해 연결 테이블이 생성됨
    * 이 테이블은 양쪽 테이블의 참조키를 칼럼으로 하며,
    * 다른 칼럼을 추가할 수 없는 단점이 있음
    * */
    @ManyToMany
    @JoinTable(name="MEMBER_PRODUCT")
    List<Product> products = new ArrayList<>();
}
