package com.example.demo.manytoone;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Team {
    @Id
    @GeneratedValue
    @Column(name="team_id")
    private Long id;

    private String name;

    /*
    */
//    @OneToMany
//    @JoinColumn(name="team_id")
//    private List<Member> members = new ArrayList<>();


}
