package com.example.demo.relation.unionetomany;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class main {

    @Autowired
    EntityManagerFactory emf;

    public void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member member = new Member();
        member.setName("test");
        em.persist(member);

        Team team = new Team();
        team.setName("team");
        team.getMembers().add(member);
        //1:N 단방향, 연관 관계 주인인 Team 엔티티를 저장하면서
        //Member 엔티티에 참조키 team_id를 업데이트 하는 쿼리가 추가로 실행됨
        //1:N 양방향으로 설정하면 UPDATE 쿼리가 실행 안됨
        em.persist(team);
        tx.commit();
    }
}
