package com.example.demo.relation.unionetomany;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Team {
    @Id
    @GeneratedValue
    @Column(name="team_id")
    private Long id;

    private String name;

    /*
    1:N 단방향, 참조키가 Member에 위치하지만 Team 엔티티가 관계 주인이다.
    Team 엔티티 INSERT시 Member의 참조키를 UPDATE하는 쿼리가 추가로 실행됨
    @JoinColumn을 생락하면 기본 값으로 @JoinTable이 사용됨(테이블 추가 생성)
    */
    @OneToMany
    @JoinColumn(name="team_id")
    private List<Member> members = new ArrayList<>();


}
