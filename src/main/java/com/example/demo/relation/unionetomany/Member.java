package com.example.demo.relation.unionetomany;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    *연관 관계 주인이지만 읽기만 가능하도록 설정
    *JPA 스펙에 공식적으로 1:N 양방향은 없음
    *이 방식 보다는 N:1 양방향을 사용하는게 좋음
    * */
    @ManyToOne
    @JoinColumn(name="team_id", insertable = false, updatable = false)
    private Team team;
}
